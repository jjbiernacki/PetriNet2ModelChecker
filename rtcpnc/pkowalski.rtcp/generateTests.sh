#!/bin/bash

cp ../../testy/testy/* ./
for i in 39 40
do
    java -jar jaRT-Generator.jar test${i}.xml Test${i}Sim
    touch Test${i}Sim/run.sh
    echo "#!/bin/bash" >> Test${i}Sim/run.sh
    echo "java -jar Test${i}Sim.jar 20" >> Test${i}Sim/run.sh
    chmod +x Test${i}Sim/run.sh
done
