using System;
using System.Collections.Generic;
using System.Linq;

namespace lab3
{
    class Program
    {
        static void Main(string[] args)
        {
            var firstShore = new Shore();
            var secondShore = new Shore();

            var firstPeri = new Person() { Pair = 1, Type = "Peri" };
            var secondPeri = new Person() { Pair = 2, Type = "Peri" };
            var thirdPeri = new Person() { Pair = 3, Type = "Peri" };

            var firstMagaradja = new Person() { Pair = 1, Type = "Magaradja" };
            var secondMagaradja = new Person() { Pair = 2, Type = "Magaradja" };
            var thirdMagaradja = new Person() { Pair = 3, Type = "Magaradja" };

            firstShore.Persons = new List<Person>() { firstPeri, secondPeri, thirdPeri, firstMagaradja, secondMagaradja, thirdMagaradja };
            var node = new Node()
            {
                FirstShore = firstShore,
                SecondShore = secondShore
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
                Console.WriteLine(node.ActionBoad);
                Console.WriteLine(node.ActionStayed);
                Console.WriteLine(node.Actionlanded);
                Console.WriteLine(new string('-', 50));
            }
        }
        static Node Algoritm(Node node)
        {
            if (IsGoal(node))
            {
                return node;
            }
            var succesors = node.ExpandNodes();

            if (succesors.Count == 0)
            {
                return null;
            }

            var minValue = succesors.Min(x => x.Value);
            succesors = succesors.Where(x => x.Value == minValue).ToList();

            foreach (var item in succesors)
            {
                var sr = Algoritm(item);

                if (sr != null)
                {
                    return sr;
                }
            }

            return null;
        }
        static bool IsGoal(Node node)
        {
            var check = node.SecondShore.Persons.Count == 6;
            return check;
        }
    }
}
