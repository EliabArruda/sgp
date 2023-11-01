#!/bin/bash

VAR=$1

if [[ $VAR -eq 1 ]]
then
  echo "TRANSFERÊNCIA DE VAB"
elif [[ $VAR -eq 2 ]]
then
  echo "2ª VIA DE DECLARAÇÃO"
elif [[ $VAR -eq 3 ]]
then
  echo "CR"
elif [[ $VAR -eq 4 ]]
then
  echo "OUTROS"
else
  echo "The code is incorrect." >&2
  exit 1;
fi

exit 0;


