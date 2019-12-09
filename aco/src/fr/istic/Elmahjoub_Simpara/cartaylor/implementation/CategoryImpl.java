package fr.istic.Elmahjoub_Simpara.cartaylor.implementation;

import fr.istic.Elmahjoub_Simpara.cartaylor.api.Category;

public  class CategoryImpl implements Category {

    private String name;
	
    public  CategoryImpl(String name)
    {
    	this.name=name;
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
	public String getName() {
		
		return this.name;
		
	}

	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		CategoryImpl other = (CategoryImpl) obj;
		
		return other.getName().equals(this.name);
	}
	

	
}
