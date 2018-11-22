export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"
antlr4 Smoola.g4
javac Smoola*.java
java org.antlr.v4.gui.TestRig Smoola -gui
