package com.digite.kata.tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TicketIssuer
{
    private ArrayList<Integer> m_availableList = null;
    private ArrayList<Integer> m_bookedList = null;
    private HashMap<Integer, Car> m_slotVsCarInfo =  new HashMap<Integer, Car>();

    public TicketIssuer(ParkingLot w_parkingLot)
    {
       if (m_availableList == null)
       {
           m_availableList = new ArrayList<Integer>();
           for (ParkingSlot slot: w_parkingLot.getSlotsInfo()) {
               m_availableList.add(slot.getParkingSlotNo());
           }
       }

    }

    public String getAllocatedSlotNo(Car a_car)
    {
    	if (m_availableList.isEmpty())
    		return "Sorry, parking lot is full";
    	
        int w_availableSlot = m_availableList.get(0);
        String w_AllocatedSlotNo = "Allocated slot number: " + w_availableSlot;
        updatelists(w_availableSlot);
        m_slotVsCarInfo.put(w_availableSlot, a_car);

        return w_AllocatedSlotNo;
    }
    

    private void updatelists(int w_availableSlot)
    {
        if(m_bookedList == null)
            m_bookedList = new ArrayList<Integer>();
        m_bookedList.add(w_availableSlot);
        m_availableList.remove(0);
        Collections.sort(m_availableList);
    }
    
    public String leaveCar(int a_slotNo)
    {
    	m_slotVsCarInfo.remove(a_slotNo);
    	m_bookedList.remove(m_bookedList.indexOf(a_slotNo));
    	m_availableList.add(a_slotNo);
    	Collections.sort(m_availableList);
    	return "Slot number " + a_slotNo + " is free";
    }

}