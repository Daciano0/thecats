clean:
	gradle clean
package: clean
	gradle assemble
build: package
	docker build -t thecat:1.0.1 .
	docker push thecat:1.0.1
tail-logs:
	docker logs -f $(shell docker ps -aqf name=thecat)
run-container: package
    docker run -d --name thecat -p 80:8080 thecat
restart-container:
	docker stop document
    docker run -d --name thecat -p 80:8080 thecat