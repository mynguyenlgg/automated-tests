#!/bin/bash
ta execute -ls "trialkey:OSEB0S-VRN06-GVDA0-CEHUX-H1NO9" -rep "SampleRepository" -prj "Scrum Board" -u "administrator" -p "04848504C58" -t "/Functional Tests/Login validation" -rs "192.168.191.215:53400" -udf "build number=" -r "Login validation" -html $HTML_RESULT -subfld "1" -subhtml "0" -tscript "{INSTALL_DIR}/binclient/taplayback.sh" -tpath "{INSTALL_DIR}/binclient/taplayback.sh"
