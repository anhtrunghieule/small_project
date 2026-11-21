JUNIT_JAR := lib/junit/junit-platform-console-standalone-6.0.3.jar
BIN_DIR   := bin
SRC_DIR   := src
DOC_DIR   := doc

.PHONY: compile run test doc clean

compile:
	mkdir -p $(BIN_DIR)
	javac -cp "$(JUNIT_JAR)" -d $(BIN_DIR) $(SRC_DIR)/*.java

run: compile
	java -cp "$(BIN_DIR)" EmojiHunterApp

test: compile
	bash -o pipefail -c 'java -jar "$(JUNIT_JAR)" execute --class-path "$(BIN_DIR)" --scan-class-path --details tree 2>&1 | awk '\''/^Failures/{skip=1} /^Test run finished/{skip=0} !skip'\'''

doc:
	mkdir -p $(DOC_DIR)
	javadoc -d $(DOC_DIR) $(filter-out $(SRC_DIR)/EmojiHunterTest.java, $(wildcard $(SRC_DIR)/*.java))

clean:
	rm -rf $(BIN_DIR)/*.class
	rm -rf $(DOC_DIR)/*