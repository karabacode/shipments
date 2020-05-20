package org.karabacode.shipments.entities;

import javax.persistence.*;

@Entity
@Table(name = "shipment_state", schema = "shipments_master", catalog = "shipments")
public class ShipmentState {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public enum States {
        ordered(1),
        paid(2),
        sent(3),
        delivered(4),
        refunded_asked(5),
        refunded(6),
        cancelled(7);
        public final long label;

        private States(long label) {
            this.label = label;
        }
    }

}
