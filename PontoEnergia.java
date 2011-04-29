/**
 * Representa um ponto de energia no mapa. Os pontos de energia recarregam a
 * energia dos agentes que estiverem próximos a ele.
 * 
 * Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
 */

class PontoEnergia extends Entidade
{	
	public PontoEnergia(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
	}
	
	public void update() {
		if(isMorta()) {
			return;
		}
		
		ganhaEnergia(Constants.PONTO_ENERGIA_REGENERA_TURNO);
		
		for(Entidade a : getArena().getEntidades()) {
			if((a instanceof Agente) && (distancia(a) <= Constants.PONTO_ENERGIA_AREA)) {
				a.ganhaEnergia(Constants.PONTO_ENERGIA_ENTREGA_TURNO);
				gastaEnergia(Constants.PONTO_ENERGIA_ENTREGA_TURNO);
				
				System.out.println("PontoEnergia"+getId()+" dando vida para " + a);
			}
		}

		System.out.println("[UPDATE] " + this);
	}
	
	public double distancia(Entidade a) {
		int x,y;
		
		x = a.getX() - getX();
		y = a.getY() - getY();
		
		return Math.sqrt(x*x - y*y);
	}
	
	public String toString() {
		return "[PontoEnergia" + getId()+"] energia="+getEnergia()+", x="+getX()+", y="+getY();
	}
}
