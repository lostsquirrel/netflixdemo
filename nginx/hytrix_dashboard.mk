

dashboard-clone:
	git clone https://github.com/Netflix/Hystrix.git

	
dashboard-init:
	mkdir -p dashboard 
	cp -rf Hystrix/hystrix-dashboard/src/main/webapp/* dashboard
	rm -rf dashboard/WEB-INF