call cd D:\work\source-my\noodlecall\noodlecall
call mvn install -P=dev -Dmaven.test.skip=true
call mvn dependency:copy-dependencies -DoutputDirectory=D:\work\source-my\noodlecall\noodlecall\noodlecall-console-web\src\main\webapp\WEB-INF\lib