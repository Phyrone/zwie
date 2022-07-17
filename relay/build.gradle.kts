plugins{
    base
}

tasks{
    clean{
        this.delete(file("target"))
    }
}