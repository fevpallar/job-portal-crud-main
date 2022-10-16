# CRU-D Mini Job Portal





<img src="https://i.postimg.cc/c15jLGQK/ww1.png" width="80"><img src="https://i.postimg.cc/KzdM05KD/ww2.png" width="80"> <img src="https://i.postimg.cc/FRPPz8Bs/ww4.png" width="80"> <img src="https://i.postimg.cc/pLZc3mJF/ww3.png" width="80">





CRU-D stands for "CReate, Update *minus* the Delete" operations. It is a quick-mini project to demonstrate common hypothetical *create & update* operations on such online *job search site*. The project was deployed in a single node Kubernate cluster.

Hardware & Sofware specification:
- Windows OS
- Memory 4Gb
- JDK 8
- Intellij IDEA
- Docker Dekstop
- Minikube
- Kubectl

Kubernate (K8S) - single node cluster
- 1 node
- 2 replica pots for the app
- 1 pot for the Database

------------


## RUNNING THE APP.

Based on Windows & Docker Dekstop 

Download the Docker Dekstop, Minikube, kubectl. See the internet to read how to set them up on different OS.

- Start the Minikube container (it will pull the *Minikube* (*K8S* engine). The image is *gcr.io/k8s-minikube/kicbase*)

`minikube start --driver=docker`

- Sync the terminal with the Kubernate VM context. 

**Note : This is important step. This is to configure Docker CLI to access Docker from Minikube VM so that later we dont run containers within our local host machine but inside minikube VM docker daemon.*

**CMD**

`@FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i`

or with **Powershell**

` & minikube -p minikube docker-env --shell powershell | Invoke-Expression`

or with **bash/zsh**

`eval $(minikube -p <profile> docker-env) `

- Pull the project image

`docker pull fevly/job-portal-crud-kube:1.0`

and pull the *MySQL 5.7* image

`docker pull MySQL 5.7`

- From the terminal get into the project root directory then apply the database deployment object and service defined in **database-deployment.yaml**

For e.g.

`D:\Fevly\job-portal-crud-main>kubectl apply -f database-deployment.yaml`

You can log the *pods* with :

`kubectl logs -f <the_pod_name>`

![4.png](https://i.postimg.cc/pL1tN5qS/4.png)

- Close the terminal. Open it and go to root project directory again and apply the app deployment object & service  (**jobportal-deployment.yaml**). There will be 2 replica pods spawn.

`kubectl apply -f jobportal-deployment.yaml`

log the app to make sure the container runs properly

![8.jpg](https://i.postimg.cc/HnM2BRjz/8.jpg)


- Start the app service with *port-forwarding* mechanism so the *app* can interact outside the cluster

See the list of services deployed along with their internal cluster IP and ports

`kubectl get svc`

then start the app service with forwarding the port

`kubectl port-forward service/<name_of_service> <outside_target_port>:<app_port>`

For e.g:

`kubectl port-forward service/job-portal-crud-svc 7080:8080`

So in this case the services availabe on http://localhost:7080/

![10-services.png](https://i.postimg.cc/nhrsx7Gb/10-services.png)


- Use Postman to access the APIs on  http://localhost:7080/

Untuk coba URL-URL (endpoint) APInya bisa di lihat di dalam folder **\job-portal-crud-main\src\main\resources**. Filenya bernama **DATA TEST.txt**

*Projectnya hanya dibangun dalam (kurang lebih) satu harian jadi tidak cukup banyak data dummy yang sempat dibuat*

------------



![11-insert.png](https://i.postimg.cc/pr1HJ9hz/11-insert.png)


------------

![12.png](https://i.postimg.cc/3RcSL218/12.png)


------------------------

## BUILD THE PROJECT FROM SCRATCH
Based on Windows OS

-  Enable WSL (Windows Subsystem for Linux)
- Start the Docker Deskstop. 
- Enter the project root directory with the Terminal
- Start the Minikube 

`minikube start --driver=docker`

It will pull the Kubernates/K8S engine image (*gcr.io/k8s-minikube/kicbase*) into the local Docker registry

- Sync the terminal with the Kubernate context. 

*Note : This is an important step. This is to configure Docker CLI to access Docker from Minikube VM so that later we dont run containers within our local host machine but inside minikube VM docker deamon.*

**CMD**

` @FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i`

**Powershell**

` & minikube -p minikube docker-env --shell powershell | Invoke-Expression`


- Pull the *mysql 5.7*  image into internal Kubernates's VM docker engine
 
* Note : The **database-deployment.yam**l mentioned previously is supposed to automatically pull this images into the local registry. However in my case, pull operation was for some unknown reasons often skipped. Hence, manually pulling needed.*

- Build the project image into the internal Kubernates's VM docker daemon

`docker build -t job-portal-crud-kube:1.0 .`

- Apply the database deployment object & service  (**database-deployment.yaml**)

`   kubectl apply -f database-deployment.yaml`

The *pod* should be now in running state, check it with `kubectl get pods`. If there are any errors then try to debug it with :

`kubectl logs <that_pod_name>`

or

`kubectl describe pods <that_pod_name>`


- Apply the app deployment object & service  (*jobportal-deployment.yaml*). There will be 2 replica *pods* spawn.

`kubectl apply -f jobportal-deployment.yaml`

*Note that If you (only if you) log the app pods and get exceptions such as "Unknown database host" or any issues related to the database connection then there is an issue with the DNS resolution. The app doesn't connect to the DB through some exposed services instead, through the DNS described in the database-deployment.yaml . This kind of problems often occurs when running Docker in Windows OS which resides inside the WSL kernel. It's known that debugging DNS issue in a local K8S cluster is quite tricky. It took me hours to debug. But here are some suggestions:*

- Restart the Kubernate `coredns` with :

`kubectl -n kube-system rollout restart deployment coredns`

- If you already have configured  some PersistentVolume(provisioned storage in the cluster ) then delete that. Or delete the entire Minikube container and start over

- read *https://stackoverflow.com/questions/65669818/dns-error-with-mysql-integration-with-kubernetes-cluster*

- If you use Docker dekstop When deploying the database-deployment.yaml make sure that you first execute:

`@FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i`

close the terminal (open new terminal) then execute the command again :

`@FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i `


then apply the database deployment object and service build descriptor

`kubectl apply -f database-deployment.yaml`

Because Cofiguring Docker CLI to access Docker from Minikube VM is somehow breaking the *DNS* resolution within the cluster.

- It's done now expose the app service then access the APIs.
