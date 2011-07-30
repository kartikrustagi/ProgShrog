from django.db import models

# Create your models here.
class Person(models.Model):
    given_name = models.CharField(max_length=50)
    surname = models.CharField(max_length=50)
    def __str__(self):
        return "%s %s"%(self.given_name,self.surname)
    class Admin:
            pass

class Pet(models.Model):
    name = models.CharField(max_length=50)
    owner = models.ForeignKey(Person)
    def __str__(self):
        return self.name #What if no self??
    class Admin:
        pass
