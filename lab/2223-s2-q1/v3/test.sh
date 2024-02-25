#!/bin/bash
set -o nounset
function control_c() {
	if [ -e "$out" ]
	then
		rm -f "$out"
	fi
}

trap control_c INT

#if [ $# -ne 1 ]
#then
#	echo "usage: $0 <main class>"
#	exit 1
#fi

ALL="Test1 Test2 Test3 Test4 Test5 Test6 Test7"
for PROG in $ALL 
do
	if [ ! -e "$PROG.class" ]
	then
		echo "$PROG.class does not exist.  Have you compiled it with make or javac?"
		exit 1
	fi

	num_failed=0
	i=1
	while true
	do
		if [ -e "inputs/$PROG.$i.in" ]
		then
			if [ "$(uname)" == "Darwin" ]
			then
				out=$(mktemp -t "$PROG")
			else
				out=$(mktemp --suffix="$PROG")
			fi
			#java "$PROG" < "inputs/$PROG.$i.in" | head -c 50MB > "$out"
			java "$PROG" < "inputs/$PROG.$i.in" > "$out"
			status="$?"
			if [ "$status" -ne "0" ]
			then
				echo "$PROG: return non-zero status $status for test case $i"
				# cat inputs/$PROG.$i.in
				num_failed=$((num_failed + 1))
			else 
				if [ -e "$out" ] 
				then
					if [ "$(diff -bB "$out" "outputs/$PROG.$i.out" | wc -l)" -ne 0 ] 
					then
						echo "$PROG test $i: failed"
						#cat inputs/$PROG.$i.in
						num_failed=$((num_failed + 1))
					else
						echo "$PROG test $i: passed"
					fi
					rm -f "$out"
				else
					echo "$PROG: cannot find output file. Execution interrupted?"
					num_failed=$((num_failed + 1))
				fi
			fi
			i=$((i + 1))
		else
			break
		fi
	done

	if [ $i -eq 1 ] 
	then
		echo "$PROG: no test cases found 🤷"
	elif [ $num_failed -eq 0 ] 
	then
		echo "$PROG: passed everything 🎉"
	fi
done
# Run style checker
# java -jar ~cs2030s/bin/checkstyle.jar -c ~cs2030s/bin/cs2030_checks.xml *.java 
# vim:noexpandtab:sw=4:ts=4
