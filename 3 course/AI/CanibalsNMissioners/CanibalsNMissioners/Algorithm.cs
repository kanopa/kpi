using System;
using System.Collections.Generic;
using System.Text;

namespace CanibalsNMissioners
{
    public class Algorithm
    {
        public Transition DLS(Transition transition, int lim)
        {
            if (transition.Goal() && lim == 0)
                return transition;
            else
            {
                if( lim > 0)
                {
                    List<Transition> transitions = transition.Transitions();
                    foreach (var item in transitions)
                    {
                        Transition result = DLS(item, lim - 1);
                        if (result != null)
                            return result;
                    }
                }
            }
            return null;
        }
        public Transition IDDFS(Transition startTransition)
        {
            for (int i = 1; ; i++)
            {
                var result = DLS(startTransition, i);
                if (result != null)
                    return result;
            }
        }
    }
}

/*
 * class Node
    {
        public string Name { get; set; }
        public List<Node> Children { get; }

        public Node()
        {

        }
        public Node(string Name)
        {
            this.Name = Name;
            Children = new List<Node>();
        }

        public Node AddChild(Node node, bool brect = true)
        {
            Children.Add(node);
            if (brect)
                node.Children.Add(this);
            return this;
        }
    }
 * private HashSet<Node> visited;
        private LinkedList<Node> path;
        private Node goal;
        private bool limitWasReached;
 * public LinkedList<Node> DLS(Node start, Node goal, int lim)
        {
            visited = new HashSet<Node>();
            path = new LinkedList<Node>();
            limitWasReached = true;
            this.goal = goal;

            DLS(start, lim);
            if (path.Count > 0)
                path.AddFirst(start);
            return path;
        }
        private bool DLS(Node node, int lim)
        {
            if (node == goal)
                return true;
            if(lim == 0)
            {
                limitWasReached = false;
                return false;
            }
            visited.Add(node);
            foreach (var child in node.Children.Where(x => !visited.Contains(x)))
            {
                if(DLS(child, lim - 1))
                {
                    path.AddFirst(child);
                    return true;
                }
            }
            return false;
        }
        public LinkedList<Node> IDDFS(Node start, Node goal)
        {
            for(int lim = 1; ;lim++)
            {
                var result = DLS(start, goal, lim);
                if (result.Count > 0 || limitWasReached)
                    return result;
            }
        }
    }
    */
