using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace lab3
{
    class Node
    {
        public Node Prev { set; get; }
        public string Actionlanded { set; get; }
        public string ActionBoad { set; get; }
        public string ActionStayed { set; get; }
        public Shore FirstShore { set; get; }
        public Shore SecondShore { set; get; }
        public int Value { set; get; }

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

        public Node GenerateMove(Shore FirstShore, Shore SecondShore, Person person, Node prev, Person secondPerson)
        {
            if (person.Type == "Peri")
            {
                var magaradji = SecondShore.Persons.FirstOrDefault(x => x.Pair == person.Pair && x.Type == "Magaradja");
                if (magaradji == null)
                {
                    return null;
                }
            }


            var firstShore = new Shore();
            var secondShore = new Shore();

            var firstShoreFirstNode = FirstShore.Persons.Where(x => x != person).ToList();

            var secondShoreFirstNode = SecondShore.Persons.ToList();
            secondShoreFirstNode.Add(person);

            firstShore.Persons = firstShoreFirstNode;
            secondShore.Persons = secondShoreFirstNode;

            var actionBoad = $"In Boad: {person.Type}, Pair: {person.Pair} and  {secondPerson.Type}, Pair: {secondPerson.Pair}";
            var actionStayed = $"Person: {secondPerson.Type}, Pair: {secondPerson.Pair} return boad to first shore";
            var action = $"Person: {person.Type}, Pair: {person.Pair} move to second shore";
            return new Node() { Prev = prev, FirstShore = firstShore, SecondShore = secondShore, Actionlanded = action, ActionBoad = actionBoad, ActionStayed = actionStayed, Value = GenerateValue(prev, person, secondPerson) };
        }
        static int GenerateValue(Node node, Person person, Person secondPerson)
        {
            if ((person.Pair == secondPerson.Pair) && (person.Type != secondPerson.Type))
            {
                return node.Value + 1;
            }
            else if ((person.Pair == secondPerson.Pair) || (person.Type != secondPerson.Type))
            {
                return node.Value + 2;
            }
            return node.Value + 3;
        }
    }
}
