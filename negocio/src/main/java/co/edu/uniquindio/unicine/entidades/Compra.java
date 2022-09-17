package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private Float precio_total;

    @Column(nullable = false)
    private LocalDateTime fecha_compra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private MedioPago medioDePago;

    //Relaciones

    @OneToMany(mappedBy = "compra")
    private List<Cupon> cupones;

    @ManyToMany
    private List<ProductoConfiteria> productosConfiteria;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Funcion funcion;

    @ManyToMany
    @JoinTable(name = "entrada",
            joinColumns = @JoinColumn(name = "compra_codigo"),
            inverseJoinColumns = @JoinColumn(name = "silla_codigo")
    )
    private List<Silla> sillas;

    @Builder
    public Compra(MedioPago medioDePago, List<Cupon> cupones, List<ProductoConfiteria> productosConfiteria, Cliente cliente, Funcion funcion, List<Silla> sillas) {
        this.fecha_compra = LocalDateTime.now();
        this.medioDePago = medioDePago;
        this.cupones = cupones;
        this.productosConfiteria = productosConfiteria;
        this.cliente = cliente;
        this.funcion = funcion;
        this.sillas = sillas;
    }
}
