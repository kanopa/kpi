goal([black,black,black,empty,white,white,white]).
init([white,white,white,empty,black,black,black]).
find([X|_],I,I,X):-
    X\=empty.
find([_|Xs],I,N,X):-
    I1 is I+1,
    find(Xs, I1,N,X).

switch([X|Xs],A,I,[X|Xn]):-
  I>1,
  I1 is I-1,
  switch(Xs, A,I1,Xn).
switch([_|Xs],A,1,[A| Xs]).

emptyhole([E|_],I,I):-
    E = empty.
emptyhole([E|Es],I,N):-
    E\= empty,
    I1 is I+1,
    emptyhole( Es,I1,N).

move(X, N ,State, State1, N-A):-
    emptyhole(State,1,A),
    switch(State,X,A,InterimState),
    switch(InterimState,empty,N,State1),
    writeln(State1).

dfslim(MaxDepth,State,_,[]):-
    MaxDepth > 0,
    goal(State).
dfslim(MaxDepth,State,History,[Move|Moves]):-
    MaxDepth > 0,
    find(State,1,N,X),
    move(X,N,State,State1,Move),
    not(member(State1,History)),
    MaxDepth1 is MaxDepth - 1,
    dfslim(MaxDepth1,State1,[State1|History],Moves).

main(Decision):-
  init(State),
  dfslim(300 ,State,[],Decision),
  write(Decision).