#!/bin/sh
#
# @author: helmedeiros
source $(dirname $0)/validateMail.sh

#VALID MAILS
for x in $(cat ./valid.txt)
do
	validateMail $x "VALID";
done

#NOT VALID MAILS
for x in $(cat ./notValid.txt)
do
	validateMail $x "NOT VALID";
done