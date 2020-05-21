member(X, [X|T]).
member(X, [H|T]) :-member(X, T).

append([], L, L).
append([X|L1], L2, [X|L3]):- append(L1, L2, L3).

reverse([],[]).
reverse([X|L1],L2):-reverse(L1,L3),append(L3,[X],L2).


move(room(X1,X2,X3,pusto,X4,X5,X6),N):-
member(N,[ room(X1,X2,pusto,X3,X4,X5,X6),room(X1,X2,X3,X4,pusto,X5,X6),
room(X1,X2,X3,X5,X4,pusto,X6),room(X1,pusto,X3,X2, X4,X5,X6)]).

move(room(X1,X2,pusto,X3,X4,X5,X6),N):-
member(N,[room(X1,pusto,X2,X3,X4,X5,X6),room(X1,X2,X3,pusto, X4,X5,X6),
room(pusto,X2,X1,X3,X4,X5,X6),room(X1,X2,X4,X3,pusto,X5,X6)]).

move(room(X1,pusto,X2,X3,X4,X5,X6),N):-
member(N,[room(pusto,X1,X2,X3,X4,X5,X6),room(X1,X2,pusto,X3, X4,X5,X6),
room(X1,X3,X2,pusto,X4,X5,X6)]).

move(room(pusto,X1,X2,X3,X4,X5,X6),N):-
member(N,[room(X1,pusto,X2,X3,X4,X5,X6),room(X2,X1,pusto,X3, X4,X5,X6)]).

move(room(X1,X2,X3,X4,pusto,X5,X6),N):-
member(N,[ room(X1,X2,X3,X4,X5,pusto,X6),room(X1,X2,X3,X4,X6, X5,pusto),
room(X1,X2,X3,pusto,X4,X5,X6),room(X1,X2,pusto,X4, X3,X5,X6)]).

move(room(X1,X2,X3,X4,X5,pusto,X6),N):-
member(N,[ room(X1,X2,X3,X4,X5,X6,pusto),room(X1,X2,X3,X4,pusto,X5,X6),
room(X1,X2,X3,pusto,X5,X4,X6)]).

move(room(X1,X2,X3,X4,X5,X6,pusto),N):-
member(N,[ room(X1,X2,X3,X4,X5,pusto,X6),room(X1,X2,X3,X4,pusto,X6,X5)]).


dsearch(Start,Goal,Way):-path(Start,Goal,[],R),reverse(R,Way).


prolong(X,Been,Y):-move(X,Y),
not(member(Y,Been)).


path(X,X,T,[X|T]).
path(Place,Y,T,R):- prolong(Place,T,Neibor), path(Neibor,Y,[Place|T],R).

solve( L ):-
dsearch( room( cherniy, cherniy, cherniy, pusto, beliy, beliy, beliy ), room( beliy, beliy, beliy, pusto, cherniy, cherniy, cherniy ), L ).

?-solve( L ),write(L).
