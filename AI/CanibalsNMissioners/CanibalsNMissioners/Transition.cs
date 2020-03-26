using System;
using System.Collections.Generic;
using System.Text;

namespace CanibalsNMissioners
{
    public class Transition
    {
        public int cannibalLeft { get; set; }
        public int cannibalRight { get; set; }
        public int cannibalBoat { get; set; }
        public int missionerLeft { get; set; }
        public int missionerRight { get; set; }
        public int missionerBoat { get; set; }
        public Boat boat { get; set; }
        public Transition parentSolution { get; set; }

        public Transition(int cannibalLeft, int missionerLeft, Boat boat,
            int cannibalRight, int missionerRight, int cannibalBoat, int missionerBoat)
        {
            this.cannibalLeft = cannibalLeft;
            this.cannibalRight = cannibalRight;
            this.cannibalBoat = cannibalBoat;
            this.missionerLeft = missionerLeft;
            this.missionerRight = missionerRight;
            this.missionerBoat = missionerBoat;
            this.boat = boat;
        }
        public bool Valid() // Умова для того щоб не було мінусових значень та щоб в човні не було білеше двух людей
        {
            if ((cannibalLeft >= 0 && cannibalRight >= 0 && missionerLeft >= 0 && missionerRight >= 0 && cannibalBoat >= 0 && missionerBoat >= 0) &&
                (missionerRight == 0 || cannibalRight == missionerRight || cannibalRight == 0) &&
                (missionerLeft == 0 || cannibalLeft == missionerLeft || cannibalLeft == 0) && (cannibalBoat + missionerBoat <= 2))
                return true;

            return false;
        }
        public bool Goal() // Рішення задачі
        {
            return cannibalLeft == 0 && missionerLeft == 0 && boat == Boat.Right;
        }
        public void CheckGoal(List<Transition> childTransition, Transition transition) // Перевірка валідності
        {
            if (transition.Valid())
            {
                transition.parentSolution = this;
                childTransition.Add(transition);
            }
        }
        public List<Transition> Transitions() // Можливі переходи
        {
            List<Transition> transitions = new List<Transition>();

            if (boat == Boat.Left)
            {
                CheckGoal(transitions, new Transition(cannibalLeft - 2, missionerLeft, Boat.LeftToRight, cannibalRight, missionerRight, cannibalBoat + 2, missionerBoat));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft - 2, Boat.LeftToRight, cannibalRight, missionerRight, cannibalBoat, missionerBoat + 2));
                CheckGoal(transitions, new Transition(cannibalLeft - 1, missionerLeft - 1, Boat.LeftToRight, cannibalRight, missionerRight, cannibalBoat + 1, missionerBoat + 1));
                CheckGoal(transitions, new Transition(cannibalLeft - 1, missionerLeft, Boat.LeftToRight, cannibalRight, missionerRight, cannibalBoat + 1, missionerBoat));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft - 1, Boat.LeftToRight, cannibalRight, missionerRight, cannibalBoat, missionerBoat + 1));
            }

            if (boat == Boat.LeftToRight)
            {

                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.Right, cannibalRight + 2, missionerRight, cannibalBoat - 2, missionerBoat));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.Right, cannibalRight, missionerRight + 2, cannibalBoat, missionerBoat - 2));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.Right, cannibalRight + 1, missionerRight + 1, cannibalBoat - 1, missionerBoat - 1));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.Right, cannibalRight + 1, missionerRight, cannibalBoat - 1, missionerBoat));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.Right, cannibalRight, missionerRight + 1, cannibalBoat - 1, missionerBoat));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.RightToLeft, cannibalRight - 1, missionerRight + 2, cannibalBoat + 1, missionerBoat - 2));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.RightToLeft, cannibalRight + 2, missionerRight - 1, cannibalBoat - 2, missionerBoat + 1));

            }

            if (boat == Boat.Right)
            {
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.RightToLeft, cannibalRight - 2, missionerRight, cannibalBoat + 2, missionerBoat));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.RightToLeft, cannibalRight, missionerRight - 2, cannibalBoat, missionerBoat + 2));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.RightToLeft, cannibalRight - 1, missionerRight - 1, cannibalBoat + 1, missionerBoat + 1));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.RightToLeft, cannibalRight - 1, missionerRight, cannibalBoat + 1, missionerBoat));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft, Boat.RightToLeft, cannibalRight, missionerRight - 1, cannibalBoat, missionerBoat + 1));

            }

            if (boat == Boat.RightToLeft)
            {
                CheckGoal(transitions, new Transition(cannibalLeft + 2, missionerLeft, Boat.Left, cannibalRight, missionerRight, cannibalBoat - 2, missionerBoat));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft + 2, Boat.Left, cannibalRight, missionerRight, cannibalBoat, missionerBoat - 2));
                CheckGoal(transitions, new Transition(cannibalLeft + 1, missionerLeft + 1, Boat.Left, cannibalRight, missionerRight, cannibalBoat - 1, missionerBoat - 1));
                CheckGoal(transitions, new Transition(cannibalLeft + 1, missionerLeft, Boat.Left, cannibalRight, missionerRight, cannibalBoat - 1, missionerBoat));
                CheckGoal(transitions, new Transition(cannibalLeft, missionerLeft + 1, Boat.Left, cannibalRight, missionerRight, cannibalBoat, missionerBoat - 1));
                CheckGoal(transitions, new Transition(cannibalLeft + 1, missionerLeft - 2, Boat.LeftToRight, cannibalRight, missionerRight, cannibalBoat - 1, missionerBoat + 2));
                CheckGoal(transitions, new Transition(cannibalLeft - 2, missionerLeft + 1, Boat.LeftToRight, cannibalRight, missionerRight, cannibalBoat + 2, missionerBoat - 1));
            }

            return transitions;
        }
    }
}
