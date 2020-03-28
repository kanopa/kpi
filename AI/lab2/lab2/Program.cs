using System;
using System.Collections.Generic;

namespace lab2
{
    class Bucket
    {
        public int Capacity { set; get; }
        public int CurrentOccupancy { set; get; }
    }
    class Node
    {
        public Node Prev { set; get; }
        public Bucket FirstBucket { set; get; }
        public Bucket SecondBucket { set; get; }
        public string Action { set; get; }
    }
    class Program
    {
        static void Main(string[] args)
        {
            var firstBucket = new Bucket()
            {
                Capacity = 9,
                CurrentOccupancy = 0
            };
            var secondBucket = new Bucket()
            {
                Capacity = 5,
                CurrentOccupancy = 0
            };
            var node = new Node()
            {
                FirstBucket = firstBucket,
                SecondBucket = secondBucket
            };

            var sol = Algoritm(node);

            if (sol != null)
            {
                Print(sol);
            }

        }
        static void Print(Node node)
        {
            if (node.Prev != null)
            {
                Print(node.Prev);
                Console.WriteLine(node.Action + $"State: First Bucket: {node.FirstBucket.CurrentOccupancy} , Second Bucket: {node.SecondBucket.CurrentOccupancy}");
            }
        }
        static Node Algoritm(Node node)
        {
            List<Node> nodes = GenerateNodes(node);
            while (true)
            {
                List<Node> temp = new List<Node>();

                foreach (var item in nodes)
                {
                    temp.AddRange(GenerateNodes(item));
                }

                nodes = temp;

                foreach (var item in nodes)
                {
                    if (IsGoal(item))
                    {
                        return item;
                    }
                }
            }
        }
        static List<Node> GenerateNodes(Node node)
        {
            List<Node> nodes = new List<Node>();


            if (node.FirstBucket.CurrentOccupancy > 0)
            {
                if (node.SecondBucket.CurrentOccupancy != node.SecondBucket.Capacity)
                {
                    nodes.Add(PourFirstToSecondBucket(node));
                }
                if (node.FirstBucket.CurrentOccupancy != node.FirstBucket.Capacity)
                {
                    nodes.Add(FillFirstBucket(node));
                }
                nodes.Add(EmptyFirstBucket(node));
            }
            else
            {
                nodes.Add(FillFirstBucket(node));
            }



            if (node.SecondBucket.CurrentOccupancy > 0)
            {
                if (node.FirstBucket.CurrentOccupancy != node.FirstBucket.Capacity)
                {
                    nodes.Add(PourSecondToFirstBucket(node));
                }
                if (node.SecondBucket.Capacity != node.SecondBucket.CurrentOccupancy)
                {
                    nodes.Add(FillSecondBucket(node));
                }
                nodes.Add(EmptySecondBucket(node));
            }
            else
            {
                nodes.Add(FillSecondBucket(node));
            }

            return nodes;
        }
        static Node FillFirstBucket(Node node)
        {
            return new Node()
            {
                Prev = node,
                FirstBucket = new Bucket() { Capacity = 9, CurrentOccupancy = 9 },
                SecondBucket = new Bucket() { Capacity = node.SecondBucket.Capacity, CurrentOccupancy = node.SecondBucket.CurrentOccupancy },
                Action = "Fill the first Bucket"
            };
        }
        static Node FillSecondBucket(Node node)
        {
            return new Node()
            {
                Prev = node,
                FirstBucket = new Bucket() { Capacity = node.FirstBucket.Capacity, CurrentOccupancy = node.FirstBucket.CurrentOccupancy },
                SecondBucket = new Bucket() { Capacity = 5, CurrentOccupancy = 5 },
                Action = "Fill the second Bucket"
            };
        }
        static Node EmptyFirstBucket(Node node)
        {
            return new Node()
            {
                Prev = node,
                FirstBucket = new Bucket() { Capacity = node.FirstBucket.Capacity, CurrentOccupancy = 0 },
                SecondBucket = new Bucket() { Capacity = node.SecondBucket.Capacity, CurrentOccupancy = node.SecondBucket.CurrentOccupancy },
                Action = "Empty the first bucket"
            };
        }
        static Node EmptySecondBucket(Node node)
        {
            return new Node()
            {
                Prev = node,
                FirstBucket = new Bucket() { Capacity = node.FirstBucket.Capacity, CurrentOccupancy = node.FirstBucket.CurrentOccupancy },
                SecondBucket = new Bucket() { Capacity = node.SecondBucket.Capacity, CurrentOccupancy = 0 },
                Action = "Empty the second bucket"
            };
        }



        static Node PourFirstToSecondBucket(Node node)
        {
            var firstBucket = new Bucket() { Capacity = node.FirstBucket.Capacity };
            var secondBucket = new Bucket() { Capacity = node.SecondBucket.Capacity };

            var freeSpaceInSecond = node.SecondBucket.Capacity - node.SecondBucket.CurrentOccupancy;

            if (node.FirstBucket.CurrentOccupancy >= freeSpaceInSecond)
            {
                secondBucket.CurrentOccupancy = 5;
                firstBucket.CurrentOccupancy = node.FirstBucket.CurrentOccupancy - freeSpaceInSecond;
            }
            else
            {
                secondBucket.CurrentOccupancy += node.FirstBucket.CurrentOccupancy;
                firstBucket.CurrentOccupancy = 0;
            }



            return new Node()
            {
                Prev = node,
                FirstBucket = firstBucket,
                SecondBucket = secondBucket,
                Action = "Pour First To Second Bucket"
            };
        }
        static Node PourSecondToFirstBucket(Node node)
        {
            var firstBucket = new Bucket() { Capacity = node.FirstBucket.Capacity };
            var secondBucket = new Bucket() { Capacity = node.SecondBucket.Capacity };

            var freeSpaceInFirst = node.FirstBucket.Capacity - node.FirstBucket.CurrentOccupancy;

            if (node.SecondBucket.CurrentOccupancy >= freeSpaceInFirst)
            {
                firstBucket.CurrentOccupancy = 9;
                secondBucket.CurrentOccupancy = node.SecondBucket.CurrentOccupancy - freeSpaceInFirst;
            }
            else
            {
                firstBucket.CurrentOccupancy += node.SecondBucket.CurrentOccupancy;
                secondBucket.CurrentOccupancy = 0;
            }


            return new Node()
            {
                Prev = node,
                FirstBucket = firstBucket,
                SecondBucket = secondBucket,
                Action = "Pour Second To First Bucket"
            };
        }


        static bool IsGoal(Node node)
        {
            var result = 3;
            return node.FirstBucket.CurrentOccupancy == result || node.SecondBucket.CurrentOccupancy == result;
        }
    }
}
