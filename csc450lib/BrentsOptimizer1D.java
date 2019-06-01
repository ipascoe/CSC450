/**
 * BrentsOptimizer1D.java
 */
package csc450lib;

/**
 * @author Ian Pascoe
 * Created 4-15-2019
 * 
 * This class implements Brent's Method of optimization using derivatives.
 */
public class BrentsOptimizer1D extends Optimizer1D {
	
	/**
	 * This algorithm was adapted from "Numeric Recipes in C" but needed many
	 * changes to work with Java.
	 * 
	 * @param f The function to find the minimum.
	 * @param a The lower bound of the search bracket.
	 * @param b The upper bound of the search bracket.
	 * @param tol The desired machine accuracy for the algorithm.
	 * @return The x-value of the found minimum.
	 */
	public float solve(Function1D f, float a, float b, float tol) {
		int ITMAX=100;
		float ZEPS=1e-7f;
		boolean ok1,ok2;
		int iter;
		float ax=a,bx=b,d=0,d1,d2,du,dv,dw,dx,e=0;
		float R=0.61803399f;
		float cx = b + R*(b - a);
		float fu,fv,fw,fx,olde,tol1,tol2,u,u1,u2,v,w,x,xm,xmin=0;

		a=(ax < cx ? ax : cx);
		b=(ax > cx ? ax : cx);
		
		x=w=v=bx;
		fw=fv=fx=f.func(x);
		dw=dv=dx=f.dfunc(x);
		
		for (iter=0;iter<ITMAX;iter++) {
			xm=0.5f*(a+b);
			tol1=tol*Math.abs(x)+ZEPS;
			tol2=2.0f*tol1;
			
			if (Math.abs(x-xm) <= (tol2-0.5*(b-a))) {
				xmin=x;
				return xmin;
			}
			
			if (Math.abs(e) > tol1) {
				d1=2.0f*(b-a);
				d2=d1;
				
				if (dw != dx) d1=(w-x)*dx/(dx-dw);
				if (dv != dx) d2=(v-x)*dx/(dx-dv);
				
				u1=x+d1;
				u2=x+d2;
				
				ok1 = (a-u1)*(u1-b) > 0.0 && dx*d1 <= 0.0;
				ok2 = (a-u2)*(u2-b) > 0.0 && dx*d2 <= 0.0;
				
				olde=e;
				e=d;
				
				if (ok1 || ok2) {
					if (ok1 && ok2)
						d=(Math.abs(d1) < Math.abs(d2) ? d1 : d2);
					else if (ok1)
						d=d1;
					else
						d=d2;
					
					if (Math.abs(d) <= Math.abs(0.5*olde)) {
						u=x+d;
						if (u-a < tol2 || b-u < tol2)
							d=Math.copySign(tol1,xm-x);
					} else {
						d=0.5f*(e=(dx >= 0.0 ? a-x : b-x));
					}
					
				} else {
					d=0.5f*(e=(dx >= 0.0 ? a-x : b-x));
				}
				
			} else {
				d=0.5f*(e=(dx >= 0.0 ? a-x : b-x));
			}
			
			if (Math.abs(d) >= tol1) {
				u=x+d;
				fu=f.func(u);
			} else {
				u=x+Math.copySign(tol1,d);
				fu=f.func(u);
				if (fu > fx) {
					xmin=x;
					return fx;
				}
			}
			
			du=f.dfunc(u);
			if (fu <= fx) {
				if (u >= x) a=x; else b=x;
				v=w;
				fv=fw;
				dv=dw;

				w=x;
				fw=fx;
				dw=dx;
				
				x=u;
				fx=fu;
				dx=du;
			} else {
				if (u < x) a=u; else b=u;
				if (fu <= fw || w == x) {
					v=w;
					fv=fw;
					dv=dw;

					w=u;
					fw=fu;
					dw=du;
				} else if (fu < fv || v == x || v == w) {
					v=u;
					fv=fu;
					dv=du;
				}
			}
		}
		return 0.0f;
	}

}
