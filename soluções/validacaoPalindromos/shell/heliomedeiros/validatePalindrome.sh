#!/bin/sh
#
# @author: helmedeiros
regex="^"; #Não deve considerar de qualquer lugar, sempre do inicio de uma frase;
regex=$regex"(.?)"; #primeiro grupo de um caractere opcional que quando ocorrer existir'a para ser repetido em \1
regex=$regex".?"; #pode haver espaço
regex=$regex"(.?)"; #segundo grupo de um caractere opcional que quando ocorrer existir'a para ser repetido em \2
regex=$regex".?"; #pode haver espaço
regex=$regex"(.?)"; #terceiro grupo de um caractere opcional que quando ocorrer existir'a para ser repetido em \3
regex=$regex".?"; #pode haver espaço
regex=$regex"(.?)"; #quarto grupo de um caractere opcional que quando ocorrer existir'a para ser repetido em \4
regex=$regex".?"; #pode haver espaço
regex=$regex"(.?)"; #quinto grupo de um caractere opcional que quando ocorrer existir'a para ser repetido em \5
regex=$regex".?"; #pode haver espaço ou um caractere intermediário
regex=$regex"\5"; #quinto grupo de um caractere opcional 
regex=$regex"\4"; #quarto grupo de um caractere opcional
regex=$regex"\3"; #terceiro grupo de um caractere opcional
regex=$regex"\2"; #segundo grupo de um caractere opcional
regex=$regex"\1"; #primeiro grupo de um caractere opcional
regex=$regex"$" #Naté final da mesma

function validatePalindrome(){
	if [ $# -ge 1 ]; then
			
		echo "VALIDATING:"$1"-> EXPECTED:"$2;
	
		if [[ $1 =~ $regex ]] ; then
    		echo "PALINDROME \n";
		else
    		echo "not PALINDROME \n";
		fi
	fi
}

function showMeRegex(){
	echo $regex;
}

showMeRegex;
validatePalindrome $@;

