# improvedPSO_VMallocation
Virtual machine (VM) allocation in dynamic Cloud environment using Improved Particle Swarm Optimization (improved PSO)
    In cloud computing, one of main components in cloud computing is data center, storing its infrastructures and servers for running cloud services. Each data center needs massive energy to running all servers and facilities round the clock. Many researches aim to reduce energy consumption of data center. However, reducing energy consumption has an impact on cloud service capability which leads to performance degradation. This research aims to reduce energy consumption and balance performance and throughput of cloud services. We applied Improved Multi-Objective Discrete Particle Swarm Optimization (IMODPSO) to virtual machine (VM) allocation. The IMODPSO combines turbulence (mutation) operator and archive management method with modified discrete Particle Swarm Optimization (PSO) which helps make population more diverse and manage all non-dominated results respectively. We use CloudSim-Plus toolkit to do simulation in various environments whose number of used VMs varied from 50 to 300. We evaluated results in terms of energy consumption, imbalanced level, and CPU utilization percentage by comparing with results from single-objective PSO. 

There are consisted of 2 folders inside:

1. cloudsim-plus-master: Java simulation framework for various kinds of cloud services from infrastructure level (IaaS) to software level (SaaS)

2. VmAllocationPSOResearch: (Modified) simulation implementation part
It is consists of 7 main classes:
  (1) Particle: the smallest unit in PSO. It consists of identification number (id), the best position, the best position fitness, and lists of VMs, hosts, velocity, and position. The main functions in Particle are calculating fitness and managing best position of a particle. 
When particle is constructed, host will be assigned to each VM randomly. Assigned host is collected as a position of each VM in position list. Velocity list is generated using position as previous host and random host as next host. After that, id is assigned to a particle and particle’s fitness is calculated. This fitness is used for selecting best position of own particle and searching for non-dominated solutions. 
  (2) Velocity: gives direction from current state to better state, velocity is composed of 2 components: previous host and next host. Velocity is used as main operation to drive particles toward the best destination (best solution). 
  (3) Swarm: is composed of list of particles and its archive. Archive is a storage storing nondominated solutions of the swarm. The main functions in Swarm class are handling archive when choosing solution for velocity update equation, adding solution, removing solution when archive is full. 
 When swarm is constructed, list of particles will be assigned. Each particle’s fitness is calculated to compare with the others in swarm. Particle whose fitness is non-dominated by any other members in swarm will be added into archive. 
  (4) Archive:composed of 2 components: position’s list of non-dominated solution and its fitness. In the end of optimization method, archive’s element position list is used for real VM assignment. 
  (5) ParticleSwarmOptimization  
ParticleSwarmOptimization class is main operation of this optimization. It is consisted of a swarm. This class’s function is to find the final solutions of the problem.  
The function is to update velocity and position using discrete operators (transition, multiplication, subtraction, and addition), do mutation operation if following the condition, update best position of own particle and non-dominated solutions of all particles in every iterations. 
  (6) VmAllocationPolicyPSO 
VmAllocationPolicyPSO class is VM allocation policy using IMODPSO. This class is modified from VMAllocationPolicyAbstract which is original class in CloudSim-Plus framework. Moreover, this class performs as a bridge to connect between our improved algorithm and simulated cloud operation. There are 2 main functions: VM allocation and deallocation functions. VM allocation function called ParticleSwarmOptimization class’s function to operate. Then, output of that function is used as VM allocation position to allocate VM.  
  (7) Test 
Test class is composed of simulation environment details. In test class, datacenter, hosts, VMs, and cloudlets (tasks) characteristics are added for creating simulation environment. VMAllocationPolicyPSO is applied when data center is created. Test class simulates whole cloud environment, so we can do experiment using modified approach.

