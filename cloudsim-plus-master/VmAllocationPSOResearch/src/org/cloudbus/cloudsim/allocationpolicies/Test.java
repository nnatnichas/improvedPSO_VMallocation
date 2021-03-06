package org.cloudbus.cloudsim.allocationpolicies;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.brokers.DatacenterBroker;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.datacenters.DatacenterCharacteristics;
import org.cloudbus.cloudsim.datacenters.DatacenterCharacteristicsSimple;
import org.cloudbus.cloudsim.datacenters.power.PowerDatacenter;
import org.cloudbus.cloudsim.distributions.UniformDistr;
import org.cloudbus.cloudsim.hosts.power.PowerHost;
import org.cloudbus.cloudsim.hosts.power.PowerHostUtilizationHistory;
import org.cloudbus.cloudsim.power.models.PowerModel;
import org.cloudbus.cloudsim.power.models.PowerModelLinear;
import org.cloudbus.cloudsim.power.models.PowerModelSpecPowerHpProLiantMl110G4Xeon3040;
import org.cloudbus.cloudsim.power.models.PowerModelSpecPowerHpProLiantMl110G5Xeon3075;
import org.cloudbus.cloudsim.power.models.PowerModelSpecPowerIbmX3550XeonX5675;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.ResourceProvisioner;
import org.cloudbus.cloudsim.provisioners.ResourceProvisionerSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.schedulers.cloudlet.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.schedulers.vm.VmScheduler;
import org.cloudbus.cloudsim.schedulers.vm.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModel;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelFull;
import org.cloudbus.cloudsim.vms.power.PowerVm;
import org.cloudsimplus.builders.tables.CloudletsTableBuilder;
import org.cloudsimplus.examples.PowerExample;

public class Test {
	 private static final int SCHEDULING_INTERVAL = 100;
	    private static final int HOSTS = 100;
	    //private static final int HOST_PES = 2; 

	    private static final int VMS = 50;
	    private static final int VM_PES = 1; // not sure*

	    private static final int CLOUDLETS = 50;
	    private static final int CLOUDLET_PES = 1; // not sure*
	    private static final int CLOUDLET_LENGTH = 432000;
	    /**
	     * Defines the minimum percentage of power a Host uses,
	     * even it it's idle.
	     */
	    //public static final double STATIC_POWER_PERCENT = 0.7;
	    /**
	     * The max number of watts/second of power a Host uses.
	     */
	    //public static final int MAX_POWER = 1000;

	    private final CloudSim simulation;
	    private DatacenterBroker broker0;
	    private List<PowerVm> vmList;
	    private List<Cloudlet> cloudletList;
	    private PowerDatacenter datacenter0;
	    private final List<PowerHostUtilizationHistory> hostList;

	    public static void main(String[] args) {
	        new Test();
	    }

	    public Test() {
	    	simulation = new CloudSim();
	        hostList = new ArrayList<>(HOSTS);
	        vmList = createPowerVms();
	        datacenter0 = createPowerDatacenter();
	        //Creates a broker that is a software acting on behalf a cloud customer to manage his/her VMs and Cloudlets
	        broker0 = new DatacenterBrokerSimple(simulation);

	        
	        cloudletList = createCloudlets();
	        broker0.submitVmList(vmList);
	        broker0.submitCloudletList(cloudletList);

	        simulation.start();

	        final List<Cloudlet> finishedCloudlets = broker0.getCloudletFinishedList();

	        new CloudletsTableBuilder(finishedCloudlets).build();
	        printHostCpuUtilizationAndPowerConsumption();
	        long cloudletLengthCheck = 0;
	        System.out.print("\n Cloudlet list: ");
	        for(int i=0 ;i<this.cloudletList.size();i++) {
	        	//System.out.println("id:"+i+" "+this.cloudletList.get(i).getTotalLength()+" ");
	        	System.out.print(this.cloudletList.get(i).getTotalLength()+", ");
	        	cloudletLengthCheck=cloudletLengthCheck+this.cloudletList.get(i).getTotalLength();
	        }
	        System.out.println("total cloudlet length check: "+cloudletLengthCheck);
	    }

	    private void printHostCpuUtilizationAndPowerConsumption() {
	        System.out.println();
	        double energyConsumption=0.0;
	        double CPUutilization=0.0;
	        double imbalanceLevel =0.0;
	        boolean[] checkActive = new boolean[hostList.size()];
	        int index=0;
	        int active =0;
	        double[] CPUutilizationHost = new double [ hostList.size()];
	        
	        for (PowerHostUtilizationHistory host : hostList) {
	            System.out.printf("Host %4d CPU utilization and power consumption\n", host.getId());
	            /*
	            Since the utilization history are stored in the reverse chronological order,
	            the values are presented in this way.
	             */
	            final double[] utilizationPercentHistory = host.getUtilizationHistory();
	            checkActive[index]=hostList.get(index).isActive();
	            if(checkActive[index]==true) {
	            	active++;
	            }
	            double totalPower = 0;
	            double time = simulation.clock();
	            double utilizationHost =0.0;
	            int uTime =0;
	            for (int i = 0; i < utilizationPercentHistory.length; i++) {
	                final double utilizationPercent = utilizationPercentHistory[i];
	                /**
	                 * The power consumption is returned in Watts/Second,
	                 * but it's measured only the instantaneous consumption for a given time,
	                 * according to the time interval defined by {@link #SCHEDULING_INTERVAL} set to the Datacenter.
	                 * For instance, for the time interval equal to 10,
	                 * It is measured the power consumption for instants, 10, 20 and so on.
	                 * That means it's not computed the power consumption for each time interval
	                 * of 10 seconds, but the power consumption at the 10th, 20th second and so on.
	                 * This way, to get the total power consumed for each 10 seconds interval,
	                 * the power consumption is multipled by the time interval.
	                */
	                final double wattsPerInterval = host.getPowerModel().getPower(utilizationPercent)*SCHEDULING_INTERVAL;
	                totalPower += wattsPerInterval;
	                utilizationHost=utilizationHost+utilizationPercent;
	                uTime++;
	                System.out.printf("\tTime %6.0f | CPU Utilization %6.10f%% | Power Consumption: %8.2f Watts in %d Seconds\n",
	                    time, utilizationPercent*100, wattsPerInterval, SCHEDULING_INTERVAL);
	                time -= SCHEDULING_INTERVAL;
	            }
	            CPUutilization = CPUutilization+ (utilizationHost/uTime);
	            CPUutilizationHost[index]=CPUutilization;
	            index++;
	            if(utilizationPercentHistory.length==1) {
	            totalPower=totalPower*4;
	           	}
		        System.out.printf(
		           "\t    Total Host %4d Power Consumption in %6.0f seconds: %10.2f Watts (average of %.2f Watts/Second) \n\n",
		           host.getId(), simulation.clock(), totalPower, totalPower/simulation.clock());
		           energyConsumption= energyConsumption + (totalPower);
	            
	        }
	        double cpuAvg = CPUutilization/active;
	        for(int i=0;i<CPUutilizationHost.length;i++) {
	        	imbalanceLevel =imbalanceLevel+Math.sqrt(Math.pow(CPUutilizationHost[i]-cpuAvg, 2));
	        }
	        System.out.printf("Total Energy Consumption = %.2f Watts", energyConsumption);
	        //System.out.printf("\nEnergy Consumption = %.2f kWh", energyConsumption/1000);
	        System.out.print("\nDatacenter "+this.datacenter0.getPowerInKWattsHour());
	        System.out.print("\nUtilization: "+CPUutilization);
	        System.out.print("\nImbalance Level: "+imbalanceLevel);
	        
	        System.out.print("\nactive host id:\n");
	        /*for(int i=0;i<checkActive.length;i++) {
	        	if(checkActive[i]==true) {
	        		System.out.print(i+ " \n");
	        	}
	        }*/
	        System.out.print("\n utilization table: ");
	        for (int j=0; j< hostList.size();j++) {
	        	System.out.print("\n Host"+j+" : ");
	        	for (int i = 0; i < hostList.get(j).getUtilizationHistory().length; i++) {
	        		System.out.print(hostList.get(j).getUtilizationHistory()[i]+" ");
	        	}
	        }
	        
	        
	    }

	    /**
	     * Creates a {@link PowerDatacenter} and its {@link PowerHost}s.
	     */
	    private PowerDatacenter createPowerDatacenter() {
	        for(int i = 0; i < HOSTS; i++) {
	            PowerHostUtilizationHistory host = createPowerHost(i);
	            this.hostList.add(host);
	        }
	        DatacenterCharacteristics characteristics = new DatacenterCharacteristicsSimple(hostList);
	        final PowerDatacenter dc = new PowerDatacenter(simulation, characteristics, new VmAllocationPolicyPSO(this.vmList));
	        dc.setSchedulingInterval(SCHEDULING_INTERVAL);
	        return dc;
	    }
	    
	    

	    private PowerHostUtilizationHistory createPowerHost(int modelId) {
	    	List<Pe> peList;
	    	if(modelId%3 == 1) {
	    		peList = new ArrayList<>(12);
		        //List of Host's CPUs (Processing Elements, PEs)
		        
		        for (int i = 0; i < 12; i++) {
		            peList.add(new PeSimple(3000, new PeProvisionerSimple())); //not sure*
		        }
	    	}else {
	    		peList = new ArrayList<>(2);
		        //List of Host's CPUs (Processing Elements, PEs)
		        
		        for (int i = 0; i < 2; i++) {
		            peList.add(new PeSimple(3000, new PeProvisionerSimple())); //not sure*
		        }
	    	}
	    	

	        PowerModel[] powerModel = {new PowerModelSpecPowerHpProLiantMl110G4Xeon3040(), new PowerModelSpecPowerIbmX3550XeonX5675(), new PowerModelSpecPowerHpProLiantMl110G5Xeon3075()} ;

	        final long ram = 20480; //in Megabytes
	        final long bw = 10000; //in Megabits/s //check*
	        final long storage = 128000; //in Megabytes
	        final ResourceProvisioner ramProvisioner = new ResourceProvisionerSimple();
	        final ResourceProvisioner bwProvisioner = new ResourceProvisionerSimple();
	        final VmScheduler vmScheduler = new VmSchedulerTimeShared();

	        final PowerHostUtilizationHistory host = new PowerHostUtilizationHistory(ram, bw, storage, peList);
	        host.setPowerModel(powerModel[modelId%3]);
	        host
	            .setRamProvisioner(ramProvisioner)
	            .setBwProvisioner(bwProvisioner)
	            .setVmScheduler(vmScheduler);
	        return host;
	    }

	    /**
	     * Creates a list of VMs.
	     */
	    private List<PowerVm> createPowerVms() {
	        final List<PowerVm> list = new ArrayList<>(VMS);
	        for (int i = 0; i < VMS; i++) {
	        	PowerVm vm;
	        	if(i%3==0) { // type1
	        		vm = new PowerVm(i, 30, VM_PES);
	        		vm.setRam(613).setBw(100).setSize(256)
	              .setCloudletScheduler(new CloudletSchedulerTimeShared());
	        	}else if(i%3==1) { //type2
	        		vm = new PowerVm(i, 40, VM_PES);
	        		vm.setRam(870).setBw(100).setSize(256)
	              .setCloudletScheduler(new CloudletSchedulerTimeShared());
	        	}else {  //type3
	        		vm = new PowerVm(i, 50, VM_PES);
	        		vm.setRam(1740).setBw(100).setSize(256)
	              .setCloudletScheduler(new CloudletSchedulerTimeShared());
	        	}
	            
	            list.add(vm);
	        }
	        //System.out.println("vmList from test: "+list);
	        return list;
	    }

	    /**
	     * Creates a list of Cloudlets.
	     */
	    private List<Cloudlet> createCloudlets() {
	    	int count=0;
	    	int[] cLength= {107565, 27990, 20625, 19190, 17855, 16612, 31942, 27349, 23417, 20050, 1559, 1538, 1518, 1498, 22236, 17872, 14364, 11545, 3208, 2990, 2787, 2598, 7393, 5861, 4647, 3685, 2921, 2316, 491, 464, 438, 414, 391, 369, 248, 238, 229, 220, 211, 203, 276, 260, 246, 232, 219, 207, 635, 520, 426, 1932};
	        final List<Cloudlet> list = new ArrayList<>(); //Put Cloudlet list here
	        for(int i=0; i<this.CLOUDLETS;i++) {
	        	Cloudlet cloudlet =
		                new CloudletSimple(i, cLength[i], CLOUDLET_PES)
		                    .setFileSize(1024)
		                    .setOutputSize(1024)
		                    .setUtilizationModel(new UtilizationModelFull());
	        	list.add(cloudlet);
	        	count++;
	        }
	        System.out.print("\ncount cloudlet: "+count);
	    	/*final List<Cloudlet> list = new ArrayList<>();
	        UtilizationModel utilization = new UtilizationModelFull();
	        long taskLength =this.CLOUDLET_LENGTH;
	        int cloudletNo = this.CLOUDLETS;
	        int i=0;
	        while (taskLength >0) {
	        	System.out.println("TaskLength" + taskLength);
	        	
	        	if(i<cloudletNo-1 && taskLength>=10)	{
	        		//long  length = (long)(Math.random()*taskLength);
	        		UniformDistr rand = new UniformDistr (0, taskLength);
	        		long length = (long)(rand.sample()/25);
		        	while (length ==0) {
		        		length = (long)(rand.sample()/25);
		        	}
		        	Cloudlet cloudlet =
		                new CloudletSimple(i, length, CLOUDLET_PES)
		                    .setFileSize(1024)
		                    .setOutputSize(1024)
		                    .setUtilizationModel(utilization);
	        		list.add(cloudlet);
	        		taskLength= taskLength - length;
	        		
	        		System.out.println("process"+length);
	        	}else if(i==cloudletNo || taskLength<10) {
	        		long length1 = taskLength;
	        		Cloudlet cloudlet =
	                new CloudletSimple(i, length1, CLOUDLET_PES)
	                    .setFileSize(1024)
	                    .setOutputSize(1024)
	                    .setUtilizationModel(utilization);
	        		list.add(cloudlet);
	        		taskLength= taskLength - length1;
	        		System.out.println("process"+length1);
	        	}
	        	
	            
	        i++;
	        
	        //cloudletNo--;
	        }*/
	        return list;
	    }
}
