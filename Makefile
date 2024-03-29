.PHONY = compile prepare run clean

SRCDIR :=.\\src\\main\\java\\org\\tila
CLASSESDIR :=classes

define createdir
	IF not exist $(1) (mkdir$(1))
endef

define removedir
	rmdir /S /Q $(1)
endef


prepare:
	$(call createdir, ${CLASSESDIR})
compile: prepare
	javac -d classes ${SRCDIR}\\constants\\*.java ${SRCDIR}\\lexer\\*.java ${SRCDIR}\\log\\*.java ${SRCDIR}\\parser\\*.java ${SRCDIR}\\scanner\\*.java ${SRCDIR}\\utils\\*.java ${SRCDIR}\\tilaexception\\*.java ${SRCDIR}\\tree\\*.java ${SRCDIR}\\scanner\\*.java
run:
	cd ${CLASSESDIR} && java org.tila.scanner.TilaScanner $(inputFile)
clean:
	$(call removedir, ${CLASSESDIR})