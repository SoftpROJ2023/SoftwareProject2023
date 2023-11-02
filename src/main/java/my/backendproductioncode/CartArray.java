package my.backendproductioncode;

import java.util.ArrayList;


public class CartArray extends ArrayList<Cart> {
        private static final long serialVersionUID = 1L;
        @Override
        public void removeRange(int fromIndex, int toIndex) {
            super.removeRange(fromIndex, toIndex);
        }
}

