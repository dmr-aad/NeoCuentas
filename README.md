Realizar un programa en java, que  cree una BD Cuentas,que pueden ser corrientes ó a plazo, tienen una clase asociada que es Cliente , utilizando el gestor  de BD NeoDatis, en modo Cliente/Servidor en distintas máquinas virtuales, 
MÉTODOS:

1-Altas 
  
             1.1_ cuentasCorriente 
             1.2_cuentasPlazo.   
             1.3_Movimientos.

Al crear una cuenta nueva comprobaremos que no existe,y tendremos antes que crear un cliente,comprobando su existencia en la BD una cuenta puede tener más de un cliente ,que puede ya existir en otra cuenta, con lo cual hay que recuperarlo ó en caso de no existir habá que crear su objeto para añadirlo a la nueva cuenta. Para dar de alta un movimiento,tiene que existir ya la CuentaCorriente,habrá que pedir el nº de cta,buscarla,crear un nuevo objeto de la clase movimientos y añadirlo al ArrayList de la colección  Movimientos que es atributo de la clase CuentaCorriente y modificar el campo saldoActual en el objeto CuentaCorriente,que tendrá el mismo valor en ese momento que dentro del atributo del mismo nombre que está en la clase Movimientos.

 Atributos de cada clase:

C_Cuenta:

    private int numero;
    private String sucursal;
    private float saldoActual;
    private Set <C_Cliente> clientes;

C_Cliente
    
    private String dni;
    private String nombre,direccion;
    private List cuentas;

C_CuentaCorriente

 Private List movimientos;

C_CuentaPlazo

    private int intereses;
    private String fechaVencimiento;
    private float depositoPlazo;

C_Movimiento

    private Date fecha;
    private Time hora;
    private C_Cuenta cuenta;
    private char operacion;
    private float importe;
    private float saldoResultante;

2-Modificación del atributo intereses en una cuentaPlazo ya existente, recibiendo nºcta y  dni del cliente.

3-Bajas  de una cuenta Plazo de un cliente determinado ,recibiendo como parámetros el nºde cuenta y el nombre de un cliente, ya que partimos del supuesto que el cliente tiene varias ctas.

4-Crear un método que visualice todos los clientes cuyo nombre empiece por C, utilizando la interfaz Icriterion.

5-Visualiza todos los Clientes cuyo saldo en la  cuentaCorriente sea >de  200.000 euros.

6-Crea un método que obtenga el número de Clientes en números rojos.

7-crea un método que obtenga el saldo medio de las cuentasPlazo de todos los Clientes de la entidad bancaria. 

8-Crea un método que obtenga un extracto, de los movimientos realizados sobre una cuentaCorriente entre dos fechas determinadas introducidas por teclado.
