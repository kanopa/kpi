using System;
using System.Collections.Generic;

namespace lab2
{
    class Program
    {
        static void Main(string[] args)
        {
            var firstBucket = new Bucket()
            {
                Capacity = 9,
                Filled = 0
            };
            var secondBucket = new Bucket()
            {
                Capacity = 5,
                Filled = 0
            };
            var node = new Node()
            {
                FirstBucket = firstBucket,
                SecondBucket = secondBucket
            };

            var sol = node.Algoritm(node);

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
                Console.WriteLine(node.Action + $"\tState: Buckets at the moment - ({node.FirstBucket.Filled}, {node.SecondBucket.Filled})");
            }
        }
    }
}
