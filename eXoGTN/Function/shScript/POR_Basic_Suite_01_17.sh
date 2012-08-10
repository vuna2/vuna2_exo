#export HOME_TEST="/home/vuna2/java/exo-working/qa-data/qa-data-repository/DailyReports/PLF/selenium/src/suite/org/exoplatform/selenium/candidate/eXoGTN"
echo "================exoGTN Basic Action====================="
export HOME_TEST=/home/vuna2/java/exo-working/qa-data/qa-data-repository/DailyReports/PLF/selenium/src/suite/org/exoplatform/selenium/candidate/eXoGTN
cd $HOME_TEST
export ReportName=POR_Basic_Suite_01_17_$(date +"%m-%d-%Y").html
pwd
java -jar selenium-server-standalone-2.21.0.jar -userExtensions /home/vuna2/java/exo-working/test/eXo-automation/jsScript/user-extensions.js -htmlsuite "*firefox" "http://localhost:8080" $HOME_TEST/Function/BasicFunction/POR_Basic_Suite_01_17.html $HOME_TEST/Function/Reports/$ReportName
exit 0

