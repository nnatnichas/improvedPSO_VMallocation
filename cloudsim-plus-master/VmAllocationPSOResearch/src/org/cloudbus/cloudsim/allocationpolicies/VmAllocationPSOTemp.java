package org.cloudbus.cloudsim.allocationpolicies;

import java.util.*;

import org.cloudbus.cloudsim.distributions.UniformDistr;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.power.PowerHostUtilizationHistory;
import org.cloudbus.cloudsim.power.models.PowerModelSpecPowerHpProLiantMl110G4Xeon3040;
import org.cloudbus.cloudsim.util.Log;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;
import org.cloudbus.cloudsim.vms.power.PowerVm;


public class VmAllocationPSOTemp extends VmAllocationPolicyAbstract {
	
	//private ParticleSwarmOptimization pso;
	private Vm[] vmTemp ;
	private int[] results = {53, 49, 75, 33, 20, 79, 21, 43, 54, 25, 12, 75, 99, 13, 39, 20, 18, 76, 46, 83, 91, 26, 82, 65, 58, 68, 46, 80, 65, 4, 73, 75, 98, 6, 11, 7, 53, 18, 20, 4, 64, 44, 60, 27, 84, 79, 63, 78, 38, 71, 21, 41, 64, 16, 93, 31, 13, 90, 28, 92, 4, 22, 63, 22, 27, 88, 1, 59, 29, 74, 72, 91, 60, 67, 44, 72, 21, 12, 95, 51, 25, 4, 23, 12, 94, 49, 95, 34, 98, 27, 84, 58, 97, 32, 31, 23, 53, 53, 27, 5, 76, 82, 49, 56, 94, 15, 56, 72, 66, 86, 30, 82, 24, 66, 56, 58, 85, 5, 20, 84, 68, 53, 84, 23, 42, 51, 73, 24, 50, 89, 55, 11, 93, 9, 74, 67, 91, 61, 81, 22, 3, 33, 42, 80, 78, 65, 80, 19, 92, 41, 3, 22, 26, 53, 61, 17, 88, 67, 5, 76, 11, 18, 68, 87, 97, 72, 3, 74, 83, 75, 25, 87, 69, 6, 44, 25, 52, 97, 17, 87, 36, 75, 21, 54, 23, 19, 1, 54, 26, 63, 85, 93, 31, 28, 33, 23, 92, 91, 28, 0, 72, 80, 55, 59, 10, 48, 75, 91, 68, 32, 78, 85, 31, 98, 63, 5, 86, 81, 47, 50, 2, 50, 62, 45, 7, 33, 90, 35, 71, 63, 6, 67, 22, 48, 72, 40, 58, 8, 14, 95, 85, 44, 10, 92, 51, 9, 20, 0, 67, 54, 33, 72, 60, 40, 51, 66, 79, 51, 68, 48, 35, 54, 18, 32, 66, 8, 8, 56, 37, 59, 11, 72, 86, 52, 83, 80, 12, 74, 6, 70, 16, 17, 43, 11, 8, 36, 30, 92, 79, 16, 24, 95, 41, 29, 47, 57, 50, 88, 45, 85}; //Put Solution in
	private Host[] hostList;
	private Host[] resultsHost;
	private boolean result = false;
	
	
	public VmAllocationPSOTemp(List<PowerVm> temp) {
		super();
		/*System.out.print("\ntemp: "+temp);
		System.out.print("\nvmTemp: ");*/
		vmTemp = new Vm[300];
		for(int i=0; i<vmTemp.length;i++) {
			vmTemp[i] = temp.get(i);
			//System.out.print(vmTemp[i]+", "+vmTemp[i].getTotalMipsCapacity()+"| ");
		}
		
	}	
	
	public void getResults(){
		hostList= new Host[100] ;
		hostList =this.getHostList().toArray(hostList);
		System.out.print("\nCheck hostlist: ");
		for(int i =0; i<hostList.length;i++) {
			System.out.print(hostList[i].getId()+" ");
		}
		//System.out.print("\n");
		int id;
		resultsHost=new Host[vmTemp.length];
		for(int i =0; i<resultsHost.length;i++) {
			id= results[i];
			resultsHost[i]=hostList[id];
		}
		this.result=true;
	}
		/*Host[] hostList= new Host[100];
		//System.out.println("host array: ");
		for(int k=0;k<this.getHostList().size();k++) {
			hostList[k] = this.getHostList().get(k);
			//System.out.print(hostList[k]+", ");
		}
		
		Particle[] p = new Particle[40];
		
		for (int j=0; j<p.length;j++) { // population size
			p[j] = new Particle(vmTemp, hostList,j);
			//System.out.println(p[j].getPosition().toString());
			
		}
		Swarm s= new Swarm (p);
		pso = new ParticleSwarmOptimization(s);
		
		ArrayList<Particle> answer =pso.start(150,1,1.5,1,0.3);
		for(int i=0;i<answer.size();i++) {
			System.out.print("\n"+answer.get(i).getPosition().length);
		}
		this.results = new Host[answer.size()][this.vmTemp.length];
		System.out.print("\nOptimization Results: ");
		for(int i=0; i<answer.size();i++) {
			System.out.print("\n Sol"+i+" : ");
			for(int j =0 ; j<this.vmTemp.length;j++) {
				this.results[i][j]=answer.get(i).getPosition()[j];
				System.out.print(this.results[i][j]+" ");
			}
			System.out.print("|\n");
			
		}
		System.out.print("\nDone");
		result= true;
	}*/
	
	@Override
	public boolean allocateHostForVm(Vm vm) {
		if(result==false) {
			this.getResults();
		}
		
		int vmId = vm.getId();
		return allocateHostForVm(vmTemp[vmId],resultsHost[vmId]);
	}	
	
	
	@Override
	public boolean allocateHostForVm(Vm vm, Host host) {
		// TODO Auto-generated method stub
		host.setActive(true);
		if (!host.createVm(vm)) {
            return false;
        }
		
        addUsedPes(vm);
        getHostFreePesMap().put(host, getHostFreePesMap().get(host) - vm.getNumberOfPes());

        Log.printFormattedLine(
            "%.2f: %s: VM #%d has been allocated to the host #%d",
            vm.getSimulation().clock(), getClass().getSimpleName(),  vm.getId(), host.getId());
        return true;
	}

	@Override
	public void deallocateHostForVm(Vm vm) {
		// TODO Auto-generated method stub
		final Host host = vm.getHost();
        final long pes = removeUsedPes(vm);
        if (host != Host.NULL) {
            host.destroyVm(vm);
            getHostFreePesMap().put(host, getHostFreePesMap().get(host) + pes);
        }else {
        	host.setActive(false);
        }
	}

	@Override
	public Map<Vm, Host> optimizeAllocation(List<? extends Vm> vmList) {
		// TODO Auto-generated method stub
		return Collections.EMPTY_MAP;
	}
	
	
	
	
	

}
