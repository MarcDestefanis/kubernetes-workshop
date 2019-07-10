# Kubernetes workshop
> Run a French bakery

- [Technologies/tools used](#Technologies/tools used)
- [Topics covered](#Topics covered)
- [Install/configure our environment](#Install/configure our environment)
- [Understand main Kubernetes objects](#Understand main Kubernetes objects)
    - [Namespaces](#Namespaces)
    - [Nodes](#Nodes)
    - [Deployments](#Deployments)
    - [Pods](#Pods)
    - [Services](#Services)
    - [DaemonSets](#DaemonSets)
- [Configure our environment](#Configure our environment)
    - [kubectl](#kubectl)
    - [Install a hypervisor](#Install a hypervisor)
    - [Minikube](#Minikube)
    - [Kubeconfig](#Kubeconfig)
    - [Istio](#Istio)
    - [Helm](#Helm)
    - [Kubernetes plugin](#Kubernetes plugin)
- [Run a French bakery](#Run a French bakery)
    - [Open](#Open)
    - [Refine](#Refine)
    - [Expand](#Expand)
- [Future possible additions](#Future possible additions)

## Technologies/tools used

## Topics covered

## Understand main Kubernetes objects

### Namespaces

https://kubernetes.io/docs/concepts/overview/working-with-objects/namespaces/

### Nodes

### Deployments

### Pods

### Services

### DaemonSets

## Configure our environment

### kubectl

https://kubernetes.io/docs/tasks/tools/install-kubectl/#install-kubectl-on-macos

- Install on macOS using Homebrew
```bash
brew install kubernetes-cli
```

### Install a hypervisor

To run a Kubernetes cluster on our machine we need to install an hypervisor.
- Let's install VirtualBox.

https://www.virtualbox.org/wiki/Downloads

### Minikube

https://kubernetes.io/docs/tasks/tools/install-minikube/

- Install on macOS using Homebrew 
```bash
brew cask install minikube
```

- Create a local Kubernetes cluster
```bash
minikube start
```

### Kubeconfig

### Istio

### Helm

### Kubernetes plugin

## Open

- Open the shop
> Deploy bakery-service Spring Boot application
```bash
kubectl apply -f kubernetes-workshop/k8s/bakery-service
```

- A great bakery starts with a great name... What is the name of our shop?
> Query name endpoint

- Your first private client
> Query order/baguette endpoint. Baguette served!

- Let's advertise our bakery
> Access Swagger

## Refine

We have faith on the success of *I Love Baguette* bakery.
Let's extract the baguette fabric from the shop itself.

- Hire a chef from France
> Deploy our chef service
  
- Taste the baguette
> Query cook/baguette endpoint. Hmm delicious!

Deploy the version 2 of your bakery - update deployment.yaml

Check that it glues together - Query order/baguette endpoint and see service to service communication

## Expand

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