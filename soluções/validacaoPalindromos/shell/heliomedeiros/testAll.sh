#!/bin/sh
#
# @author: helmedeiros
source $(dirname $0)/validatePalindrome.sh

#VALID MAILS
for x in $(cat ../valid.txt)
do
	validatePalindrome $x "PALINDROME";
done

#NOT VALID MAILS
for x in $(cat ../inValid.txt)
do
	validatePalindrome $x "NOT PALINDROME";
done