# COSC326
# Etude 10 - Red Green

CC = javac
FILES = Main.java MegaNumber.java
FLAGS = -Xlint

build: $(FILES)
	$(CC) $(FLAGS) $(FILES)
