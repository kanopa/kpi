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
                int counter = 0;
                for (int i = depth; i >= 0; i--)
                {
                    transition = path[i];
                    if (transition.boat == Boat.LeftToRight || transition.boat == Boat.RightToLeft)
                        counter++;
                    if(transition.boat == Boat.Left || transition.boat == Boat.Right)
                    {
                        Console.ForegroundColor = ConsoleColor.Green;
                        Console.WriteLine("Cannibals L:{0}, Missioner L: {1},  --- Boat:{2}  Cannibal:{3}  Missioner: {4} ---  " +
                                        "Cannibal R: {5}, Missioner R: {6} ",
                                        transition.cannibalLeft, transition.missionerLeft, transition.boat, transition.cannibalBoat, transition.missionerBoat, transition.cannibalRight, transition.missionerRight);
                        Console.ResetColor();
                        Console.WriteLine();
                    }
                    else
                    {
                        Console.WriteLine("Cannibals L:{0}, Missioner L: {1},  --- Boat:{2}  Cannibal:{3}  Missioner: {4} ---  " +
                                        "Cannibal R: {5}, Missioner R: {6} ",
                                        transition.cannibalLeft, transition.missionerLeft, transition.boat, transition.cannibalBoat, transition.missionerBoat, transition.cannibalRight, transition.missionerRight);
                    }
                }
                Console.WriteLine("Green color - Near which shore is the boat");
                Console.WriteLine();
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("Depth: {0}", depth);
                Console.WriteLine("Transitions: {0}", counter);
                Console.WriteLine();
                Console.ResetColor();
            }
        }

    }
}
