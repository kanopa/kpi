using System;
using System.Collections.Generic;
using System.Linq;

namespace lab3
{
    class Person
    {
        public int Pair { set; get; }
        public string Type { set; get; }
    }
    class Shore
    {
        public List<Person> Persons { set; get; } = new List<Person>();
    }

    class Node
    {
        public Node Prev { set; get; }
        public string Actionlanded { set; get; }
        public string ActionBoad { set; get; }
        public string ActionStayed { set; get; }
        public Shore FirstShore { set; get; }
        public Shore SecondShore { set; get; }

        public List<Node> ExpandNodes()
        {
            List<Node> childs = new List<Node>();

            foreach (var item in FirstShore.Persons)
            {
                foreach (var item2 in FirstShore.Persons)
                {
                    if (FirstShore.Persons.Count == 1)
                    {
                        var firstNode = GenerateMove(FirstShore, SecondShore, item, this, new Person() { Pair = 0, Type = "" });

                        if (firstNode != null)
                        {
                            childs.Add(firstNode);
                        }

                    }
                    else
                    {
                        if (item.Type == "Magaradja")
                        {
                            if (item2.Type == "Magaradja")
                            {
                                if (item.Pair != item2.Pair)
                                {
                                    var firstNode = GenerateMove(FirstShore, SecondShore, item, this, item2);

                                    var secondNode = GenerateMove(FirstShore, SecondShore, item2, this, item);

                                    if (firstNode != null)
                                    {
                                        childs.Add(firstNode);
                                    }
                                    if (secondNode != null)
                                    {
                                        childs.Add(secondNode);
                                    }
                                }
                            }
                            else if (item2.Type == "Peri")
                            {
                                if (item.Pair == item2.Pair)
                                {
                                    var firstNode = GenerateMove(FirstShore, SecondShore, item, this, item2);

                                    var secondNode = GenerateMove(FirstShore, SecondShore, item2, this, item);

                                    if (firstNode != null)
                                    {
                                        childs.Add(firstNode);
                                    }
                                    if (secondNode != null)
                                    {
                                        childs.Add(secondNode);
                                    }
                                }
                            }
                        }
                        if (item.Type == "Peri")
                        {
                            if (item2.Type == "Magaradja")
                            {
                                if (item.Pair == item2.Pair)
                                {
                                    var firstNode = GenerateMove(FirstShore, SecondShore, item, this, item2);

                                    var secondNode = GenerateMove(FirstShore, SecondShore, item2, this, item);

                                    if (firstNode != null)
                                    {
                                        childs.Add(firstNode);
                                    }
                                    if (secondNode != null)
                                    {
                                        childs.Add(secondNode);
                                    }
                                }
                            }
                            if (item2.Type == "Peri")
                            {
                                if (item.Pair != item2.Pair)
                                {
                                    var firstNode = GenerateMove(FirstShore, SecondShore, item, this, item2);

                                    var secondNode = GenerateMove(FirstShore, SecondShore, item2, this, item);

                                    if (firstNode != null)
                                    {
                                        childs.Add(firstNode);
                                    }
                                    if (secondNode != null)
                                    {
                                        childs.Add(secondNode);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return childs;
        }

        public Node GenerateMove(Shore FirstShore, Shore SecondShore, Person Person, Node prev, Person secondPerson)
        {
            if (Person.Type == "Peri")
            {
                var magaradji = SecondShore.Persons.FirstOrDefault(x => x.Pair == Person.Pair && x.Type == "Magaradja");
                if (magaradji == null)
                {
                    return null;
                }
            }


            var firstShore = new Shore();
            var secondShore = new Shore();

            var firstShoreFirstNode = FirstShore.Persons.Where(x => x != Person).ToList();

            var secondShoreFirstNode = SecondShore.Persons.ToList();
            secondShoreFirstNode.Add(Person);

            firstShore.Persons = firstShoreFirstNode;
            secondShore.Persons = secondShoreFirstNode;

            var actionBoad = $"In Boad: {Person.Type}, Pair: {Person.Pair} and  {secondPerson.Type}, Pair: {secondPerson.Pair}";
            var actionStayed = $"Person: {secondPerson.Type}, Pair: {secondPerson.Pair} return boad to first shore";
            var action = $"Person: {Person.Type}, Pair: {Person.Pair} move to second shore";
            return new Node() { Prev = prev, FirstShore = firstShore, SecondShore = secondShore, Actionlanded = action, ActionBoad = actionBoad, ActionStayed = actionStayed };
        }
    }

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
