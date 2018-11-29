export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"
antlr4 Smoola.g4
javac Smoola*.java
grun Smoola program < testCases/doc-sample2.sml > outputs/doc-sample2-out.txt
grun Smoola program < testCases/doc-sample3.sml > outputs/doc-sample3-out.txt
grun Smoola program < testCases/doc-sample4.sml > outputs/doc-sample4-out.txt
grun Smoola program < testCases/doc-sample5.sml > outputs/doc-sample5-out.txt
grun Smoola program < testCases/doc-sample6.sml > outputs/doc-sample6-out.txt
grun Smoola program < testCases/1.sml > outputs/1_out.txt 
grun Smoola program < testCases/2.sml > outputs/2_out.txt 
grun Smoola program < testCases/3.sml > outputs/3_out.txt 
grun Smoola program < testCases/4.sml > outputs/4_out.txt 
grun Smoola program < testCases/5.sml > outputs/5_out.txt 
grun Smoola program < testCases/6.sml > outputs/6_out.txt 
grun Smoola program < testCases/7.sml > outputs/7_out.txt 
grun Smoola program < testCases/8.sml > outputs/8_out.txt 
grun Smoola program < testCases/9.sml > outputs/9_out.txt 

