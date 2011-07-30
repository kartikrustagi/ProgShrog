# Create your views here.
from django.shortcuts import render_to_response
from petinfosite.petinfostore.models import Person, Pet

def petview(request, owner_id=-1):
    people = Person.objects.all()
    if owner_id != -1:
        owner = Person.objects.get(id=owner_id)
        pets = Pet.objects.filter(owner = owner_id)
        return render_to_response('petinfo.html', {'people':people, 'owner':owner, 'pets':pets})
    return render_to_response('petinfo.html',{'people':people}) 
