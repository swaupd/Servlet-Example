TOMCAT = /opt/homebrew/opt/tomcat@9/libexec
WEBAPP_NAME = mywebapp
DEPLOY_DIR = $(TOMCAT)/webapps/$(WEBAPP_NAME)
SRC_DIR = src/main
JAVA_SRC = $(SRC_DIR)/java
WEB_SRC = $(SRC_DIR)/webapp
BUILD_DIR = build/classes
CP = $(TOMCAT)/lib/servlet-api.jar
JAVASOURCES := $(shell find $(JAVA_SRC) -name "*.java" 2>/dev/null)

.PHONY: build deploy clean run stop

build:
	@if [ -n "$(JAVASOURCES)" ]; then \
		echo "Compiling Java sources..."; \
		mkdir -p $(BUILD_DIR); \
		javac -d $(BUILD_DIR) -cp $(CP) $(JAVASOURCES); \
	else \
		echo "No Java files found. Skipping compilation."; \
	fi

deploy: build
	mkdir -p $(DEPLOY_DIR)/WEB-INF/classes
	rsync -a --delete $(WEB_SRC)/ $(DEPLOY_DIR)/
	@if [ -d "$(BUILD_DIR)" ]; then \
		rsync -a --delete $(BUILD_DIR)/ $(DEPLOY_DIR)/WEB-INF/classes/; \
	fi

run:
	$(TOMCAT)/bin/catalina.sh run

stop:
	$(TOMCAT)/bin/catalina.sh stop || true

clean:
	rm -rf build
	rm -rf $(DEPLOY_DIR)
