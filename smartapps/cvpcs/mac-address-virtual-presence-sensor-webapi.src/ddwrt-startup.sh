while [ ! -e "/tmp/custom.sh" ]; do
  sleep 1 # wait till /tmp/custom.sh gets generated
done
/tmp/custom.sh "40:4e:36:93:eb:16" "f0e4749a-2f8c-4729-9789-ae512911f864" "4a4f2984-5f0d-4a0a-b710-c2e87e2d11d3" &
/tmp/custom.sh "64:bc:0c:66:bc:dd" "f0e4749a-2f8c-4729-9789-ae512911f864" "4a4f2984-5f0d-4a0a-b710-c2e87e2d11d3" &