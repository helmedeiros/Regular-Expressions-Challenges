#!/bin/sh
#
# @author: helmedeiros
regex="^[a-z]"; #Deve começar com uma letra
regex=$regex"[a-z0-9_]"; #Só letras minúsculas, números e '_'
regex=$regex"*(\." #Pode conter um ponto
regex=$regex"[a-z][a-z0-9_]+)?" #se tiver ponto proximo caracter tem que ser uma letra (NAo pode ficar so o ponto por isso o +)
regex=$regex"@" #Deve ter um @
regex=$regex"[a-z]"; #Deve começar com uma letra
regex=$regex"([a-z0-9]?)" #Só letras minúsculas e números (não pode '_') (Pode só ter uma letra por isso opcional ?)
regex=$regex"+(\.[a-z]{2,4})"; #Esse "algumacoisa" deve ser composto somente de letras: Ex: "teste.org", "teste.com", "teste.com.br"
regex=$regex"{1,2}$" #Devem terminar por ".algumacoisa" ou “.alguma.coisa”, não pode ter mais de 2 pontos! Nem terminar só com ponto, ou seja, isso não pode “andre@alguma.coisa.”

function validateMail(){
	if [ $# -ge 1 ]; then
			
		echo "VALIDATING:"$1" -> EXPECTED:"$2;
	
		if [[ $1 =~ $regex ]] ; then
    		echo "VALID \n";
		else
    		echo "not VALID \n";
		fi
	fi
}

function showMeRegex(){
	echo $regex;
}

validateMail $@;

