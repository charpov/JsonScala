import scala.annotation.tailrec

case class Tree[+A](root: A, forest: Forest[A])

case class Forest[+A](trees: IndexedSeq[Tree[A]])

private def treeToList[A](tree: Tree[A]): List[Any] = tree.root :: forestToList(tree.forest)

private def forestToList[A](forest: Forest[A]): List[Any] = forest.trees.map(treeToList).toList

private def listToTree[A](list: List[Any]): Tree[A] =
   require(list.nonEmpty)
   Tree(list.head.asInstanceOf[A], listToForest(list.tail))

private def listToForest[A](list: List[Any]): Forest[A] =
   Forest(list.toIndexedSeq.map(x => listToTree(x.asInstanceOf[List[Any]])))

def writeTree[A](tree: Tree[A]): String = Json.write(treeToList(tree))

def parseTree[A](str: String): Tree[A] = listToTree(Json.parse[List[Any]](str))

val small: Tree[Int] = Tree(
  1,
  Forest(
    IndexedSeq(
      Tree(2, Forest(IndexedSeq.empty)),
      Tree(
        3,
        Forest(
          IndexedSeq(
            Tree(2, Forest(IndexedSeq.empty)),
            Tree(2, Forest(IndexedSeq.empty))
          )
        )
      )
    )
  )
)

val comb: Tree[String] =
   @tailrec def loop(tree: Tree[String], n: Int): Tree[String] =
      if n == 0 then tree
      else loop(Tree(tree.root, Forest(IndexedSeq(tree))), n - 1)
   loop(Tree("X", Forest(IndexedSeq.empty)), 100)

@main def Test(): Unit =
   val str1: String = writeTree(small)
   println(str1)
   val tree1: Tree[Int] = parseTree(str1)
   println(tree1)
   val str2: String = writeTree(comb)
   println(str2)
   val tree2: Tree[String] = parseTree(str2)
   println(tree2)
