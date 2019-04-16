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


public class VmAllocationPolicyPSO extends VmAllocationPolicyAbstract {
	
	private ParticleSwarmOptimization pso;
	private Vm[] vmTemp ;
	private Host[] results;
	private boolean result = false;
	//private List<Host> hostTemp;
	
	
	public VmAllocationPolicyPSO(List<PowerVm> temp) {
		super();
		System.out.print("\ntemp: "+temp);
		System.out.print("\nvmTemp: ");
		vmTemp = new Vm[50];
		for(int i=0; i<vmTemp.length;i++) {
			vmTemp[i] = temp.get(i);
			System.out.print(vmTemp[i]+", "+vmTemp[i].getTotalMipsCapacity()+"| ");
		}
		System.out.print("\n");
		
		
	}	
	
	public void getResults(){
		/*Vm[] vmArray = new Vm[vmTemp.size()];
		for(int i=0; i<vmTemp.size();i++) {
			vmArray[i] = vmTemp.get(i);
		}*/
		Host[] hostList= new Host[100];
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
		this.results =pso.start(150,1,1.5,1,0.3);
		result= true;
	}
	
	@Override
	public boolean allocateHostForVm(Vm vm) {
		
		
		/*boolean allocate=false;
		vmTemp.add(vm);
		
		if(vmTemp.size()==50) {
			Vm[] vmArray = new Vm[50];
			for(int i=0; i<vmTemp.size();i++) {
				vmArray[i] = vmTemp.get(i);
			}
			Host[] hostList= new Host[this.getHostList().size()];
			for(int k=0;k<this.getHostList().size();k++) {
				hostList[k] = this.getHostList().get(k);
			}
			
			Particle[] p = new Particle[40];
			for (int j=0; j<p.length;j++) { // population size
				p[j] = new Particle(vmArray, hostList);
			}
			Swarm s= new Swarm (p);
			pso = new ParticleSwarmOptimization(s);
			pso.start(150,2,3,2,10,10);
			//boolean allocateHostForEachVm=false;
			for(int m=0;m<pso.getSwarm().getgBestParticle().length;m++) {
				allocate = allocateHostForVm(vmArray[m],pso.getSwarm().getgBestParticle()[m]);
				
				
			}
			boolean remove= vmTemp.removeAll(vmTemp);
			allocate=true;
		}
		return allocate;*/
		if(result==false) {
			this.getResults();
		}
		
		int vmId = vm.getId();
		return allocateHostForVm(vmTemp[vmId],results[vmId]);
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
