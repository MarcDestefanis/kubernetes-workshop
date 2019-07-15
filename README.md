# Kubernetes workshop
> Run a French bakery & learn Kubernetes

![Kubernetes baguette logo](kubernetes-baguette.png?raw=true "Kubernetes baguette logo")

- [Technologies used](#Technologies-used)
- [Topics covered](#Topics-covered)
- [Understand main Kubernetes objects](#Understand-main-Kubernetes-objects)
    - [Namespaces](#Namespaces)
    - [Nodes](#Nodes)
    - [Pods](#Pods)
    - [Deployments](#Deployments)
    - [Services](#Services)
    - [DaemonSets](#DaemonSets)
- [Configure our environment](#Configure-our-environment)
    - [kubectl](#kubectl)
    - [Install a hypervisor](#Install-a-hypervisor)
    - [Minikube](#Minikube)
    - [Kubeconfig](#Kubeconfig)
    - [Your first kubectl command](#Your-first-kubectl-command)
    - [Helm](#Helm)
    - [Istio](#Istio)
    - [Visual Studio Code Kubernetes extension](#Visual-Studio-Code-Kubernetes-extension)
- [Run a French bakery](#Run-a-French-bakery)
    - [Open](#Open)
    - [Refine](#Refine)
    - [Expand](#Expand)
- [Future possible additions](#Future-possible-additions)

## Technologies used

## Topics covered

## Understand main Kubernetes objects

### Namespaces

> https://kubernetes.io/docs/concepts/overview/working-with-objects/namespaces/

A namespace is a virtual cluster in a physical cluster. We can have multiple virtual clusters in the same physical cluster.
When we use a specific namespace, we are not able to see the content of another namespace.
There is a strict logical boundary between namespaces.

When we use kubectl to interact with our Kubernetes cluster, we interact with a specific namespace.
We can switch between namespaces to interact with our different virtual clusters.

A use case could be for example having a namespace per team or a namespace per environment.

### Nodes

> https://kubernetes.io/docs/concepts/architecture/nodes/

A node is a physical or virtual machine represented in Kubernetes as a *worker*.
Each node contains all required components to run pods.
A Kubernetes cluster contains a set of nodes/machines with specific allocated CPU and memory.

### Pods

> https://kubernetes.io/docs/concepts/workloads/pods/pod-overview/

A pod is the smallest deployable object in Kubernetes. 
It encapsulates application's container(s).

### Deployments

> https://kubernetes.io/docs/concepts/workloads/controllers/deployment/

Deployments are wrappers on top of pods.

It allows to describe replicaSets that define the number of pod replicas for an application deployed as a pod.

In a deployment we define how to retrieve the image used by our pods and a tag as well. 
That allows us to easily rollout to a new version of our application.

We can have a deployment for each services that we deploy in Kubernetes.

### Services

> https://kubernetes.io/docs/concepts/services-networking/service/

Services define a way to access an application running on a set of pods.

### DaemonSets

> https://kubernetes.io/docs/concepts/workloads/controllers/daemonset/

A DaemonSet describes a pod that runs on each of our nodes. 
If our cluster scales out, the corresponding pod will start on this new node.

An example would be a fluentd pod that collects our application logs. 
It is required for this pod to be running on each of our nodes in which we want to collect logs.

## Configure our environment

### kubectl

> https://kubernetes.io/docs/tasks/tools/install-kubectl/#install-kubectl-on-macos

- Install on macOS using Homebrew
```bash
brew install kubernetes-cli
```

### Install a hypervisor

To run a Kubernetes cluster on our machine we need to install an hypervisor.
- Let's install VirtualBox.

https://www.virtualbox.org/wiki/Downloads

### Minikube

> https://kubernetes.io/docs/tasks/tools/install-minikube/

- Install on macOS using Homebrew 
```bash
brew cask install minikube
```

- Create a local Kubernetes cluster
> By default, minikube starts a node with only 2048MB of memory and 2cpus. 
We can use flags to configure the resources we want to allocate to our node.
```bash
minikube start --memory 5120 --cpus 4
```

#### Delete Minikube

:heavy_exclamation_mark: We are not supposed to delete Minikube, this workshop uses a local Kubernetes cluster created by Minikube.
But if needed then you know how to delete Minikube and clean your environment.

-  Delete minikube and clean our environment

```bash
minikube delete
rm -rf ~/.minikube ~/.kube
```

### Kubeconfig

> https://kubernetes.io/docs/concepts/configuration/organize-cluster-access-kubeconfig/

By default, kubectl looks for a file named config in the `$HOME/.kube` directory. 
We can specify other kubeconfig files by setting the KUBECONFIG environment variable or by setting the --kubeconfig flag when we perform kubectl commands.

- Let's have a look to our kubeconfig file created by Minikube.
```bash
cat ~/.kube/config
```

This file contains information to connect to our local Kubernetes cluster. 
We can now run kubectl commands on that cluster.

### Your first kubectl command

Now that we have Minikube installed and our local Kubernetes cluster up and running, we can start playing with our cluster.

-  Let's run our first kubectl command!

```bash
kubectl cluster-info
```

> You should get a similar output
```text
➜  ~ kubectl cluster-info
Kubernetes master is running at https://192.168.99.100:8443
KubeDNS is running at https://192.168.99.100:8443/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy
```

### Helm

> https://helm.sh/

Helm is a package manager for Kubernetes, allowing us to install, upgrade, delete components in our Kubernetes cluster.

> https://github.com/helm/helm#install

-  Install Helm client

```bash
brew install kubernetes-helm
```

### Istio

> https://istio.io/

Istio is a service mesh that will help us in our microservices journey with Kubernetes. 
It helps us on common problems/needs while running microservices. 
It adds observability, traffic management, allows canary deployment and other useful features (see official documentation).

> https://istio.io/docs/setup/kubernetes/#downloading-the-release

> Download Istio
```bash
curl -L https://git.io/getLatestIstio | ISTIO_VERSION=1.1.8 sh -
```

```bash
cd istio-1.1.8
```

> Create tiller ServiceAccount and bind it to cluster-admin ClusterRole
```bash
kubectl apply -f install/kubernetes/helm/helm-service-account.yaml
```

> Install Helm server-side component (tiller) into our cluster
```bash
helm init --service-account tiller
```

> Install Istio on our Kubernetes cluster
```bash
helm install install/kubernetes/helm/istio-init --name istio-init --namespace istio-system
```

```bash
helm install install/kubernetes/helm/istio --name istio --namespace istio-system
```

> Enable automatic sidecar (Envoy Proxy) injection
```bash
kubectl label namespace default istio-injection=enabled
```

### Visual Studio Code Kubernetes extension

This Kubernetes plugin allows us to have a view on our Kubernetes cluster(s). 
It reads our kubeconfig and embed kubectl commands to retrieve information.
We can access logs, connect to specific pods etc.

- Install Visual Studio Code

> https://code.visualstudio.com/

- Install Kubernetes extension

> https://code.visualstudio.com/docs/azure/kubernetes

Open Visual Studio Code, go to `Code>Preferences>Extensions` And search for Kubernetes. Then install the Microsoft Kubernetes extension. 

## Run a French bakery

### Open

- Open the shop
> Deploy bakery-service Spring Boot application
```bash
kubectl apply -f k8s/bakery-service
```

- Find where the shop has been created
> Retrieve the IP address of the running cluster
```bash
minikube ip
```

> Store the IP into a variable
```bash
CLUSTER_IP=$(minikube ip)
```

> Store the bakery-service nodePort into a variable
```bash
BAKERY_NODEPORT=$(kubectl get -o jsonpath="{.spec.ports[0].nodePort}" services bakery-service-svc)
```

- Check that the door is open
> Query health check endpoint
```bash
curl $CLUSTER_IP:$BAKERY_NODEPORT/actuator/health
```

- A great bakery starts with a great name... What is the name of our shop?
> Query name endpoint
```bash
curl $CLUSTER_IP:$BAKERY_NODEPORT/name
```

- Your first private client
> Query order/baguette endpoint. Baguette served!
```bash
curl $CLUSTER_IP:$BAKERY_NODEPORT/order/baguette
```

### Refine

We have faith on the success of *I Love Baguette* bakery.
Let's extract the baguette fabric from the shop itself.

- Hire a chef from France
> Deploy our chef service
```bash
kubectl apply -f k8s/chef-service
```

> Store the chef-service nodePort into a variable
```bash
CHEF_NODEPORT=$(kubectl get -o jsonpath="{.spec.ports[0].nodePort}" services chef-service-svc)
```
  
- Taste the baguette
> Query cook/baguette endpoint. Hmm delicious!
```bash
curl $CLUSTER_IP:$CHEF_NODEPORT/baguette
```


Deploy the version 2 of your bakery - update deployment.yaml

Check that it glues together - Query order/baguette endpoint and see service to service communication

### Expand

As we have more and more customers. We don't have any resources left and our chef can't handle the load.

We can open new shops to redistribute our customers baguette need.

But we would have to buy or rent new lands as well.

If the lands start to get to expensive we could as well build vertically and expand on top of our shop.

  Open new shops - HPA (Horizontal Pod Autoscaler)
  
  Rent new lands - Cluster autoscaler
  
  Build vertically - VPA (Vertical Pod Autoscaler)

## Future possible additions

Handle multiple environments staging, prod etc

Handle multi tenant k8s cluster
