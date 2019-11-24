#! /bin/sh

if [ "$1" = "" ]; then
	echo "usage: alvis2smv alvis_file [output_smv_file]"
	exit
fi

filename="$1"
output="$2"
temp="tmp_"$(date +%s)

if [ "$output" = "" ]; then
	output=${filename%.alvis}.smv
fi

echo "Compiling Alvis model..."
./alvisc/bin/alvisc $filename -o $temp.hs

echo "Compiling Haskell representation..."
ghc $temp.hs 
rm $temp.hs 

echo "Generating LTS..."
ulimit -s unlimited
./$temp > $temp.dot
rm $temp.hi
rm $temp.o
rm $temp

echo "Generating SMV file..."
java -Xmx12g -jar a2mc.jar -f $temp.dot -o $output 
rm $temp.dot 




