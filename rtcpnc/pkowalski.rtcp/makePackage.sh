#!/bin/bash

mkdir jaRT-Generator
cp -r lib/ templates/ jaRT-Generator.jar jaRT-Generator
tar -vcjf jaRT-Generator.tar.bz2 jaRT-Generator/
