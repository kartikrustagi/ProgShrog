# Create your views here.
from django.core.context_processors import csrf
from django.shortcuts import render_to_response

def form(request):
    """View to handle initial rendering of form"""
    return render_to_response('form.html')

def submit(request):
    """POST request handler"""
    c = {}
    c['name'] = request.POST['name']
    return render_to_response('showForm.html',c)
