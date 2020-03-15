using System;
using System.Collections.Generic;

namespace CanibalsNMissioners
{
    class Program
    {
        static void Main(string[] args)
        {
            Transition startTransition = new Transition(3, 3, Boat.Left, 0, 0, 0, 0);
            Algorithm algorithm = new Algorithm();
            Transition result = algorithm.IDDFS(startTransition);
            OutputSolution(result);
        }
        static void OutputSolution(Transition result)
        {
            if (result == null)
                Console.WriteLine("No solution");
            else
            {
                List<Transition> path = new List<Transition>();
                Transition transition = result;
                while (transition != null)
                {
                    path.Add(transition);
                    transition = transition.parentSolution;
                }
                int depth = path.Count - 1;
                for (int i = depth; i >= 0; i--)
                {
                    transition = path[i];
                    Console.WriteLine("cannibalsLeft:{0}, missionariesLeft: {1}, boat:{2}, cannibalsBoat:{3}, missionariesBoat: {4}, " +
                                        "cannibalsRight: {5}, missionariesRight: {6} ",
                                        transition.cannibalLeft, transition.missionerLeft, transition.boat, transition.cannibalBoat, transition.missionerBoat, transition.cannibalRight, transition.missionerRight);
                    Console.WriteLine();
                }
                Console.WriteLine("Depth: {0}", depth);
            }
        }
    }
}
