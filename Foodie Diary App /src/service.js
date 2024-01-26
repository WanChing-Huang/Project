export function fetchCards(){
    return fetch('/api/cards', {
        method: 'GET',
      })
    .catch( () => Promise.reject({ error: 'networkError' }) )
    .then(
        response => {
            if (response.ok) {
              return response.json();
            }
            return response.json()}
    )
}

export function fetchLogin( username ) {
    return fetch('/api/session', {
      method: 'POST',
      headers: new Headers({
        'content-type': 'application/json'
      }),
      body: JSON.stringify({ username }),
    })
    .catch( () => Promise.reject({ error: 'networkError' }) )
    .then( response => {
      if (response.ok) {
        return response.json();
      }
      return response.json()
      .catch( error => Promise.reject({ error }) )
      .then( err => Promise.reject(err) );
    });
  }

  export function fetchLogout() {
    return fetch('/api/session', {
      method: 'DELETE',
    })
    .catch( () => Promise.reject({ error: 'networkError' }) )
    .then( response => {
      if (response.ok) {
        return response.json();
      }
      return response.json()
      .catch( error => Promise.reject({ error }) )
      .then( err => Promise.reject(err) );
    });
  }
  
  export function fetchSession() {
    return fetch('/api/session', {
      method: 'GET',
    })
    .catch( () => Promise.reject({ error: 'networkError' }) )
    .then( response => {
      if (response.ok) {
        return response.json();
      }
      return response.json()
      .catch( error => Promise.reject({ error }) )
      .then( err => Promise.reject(err) );
    });
  }


  //fetch to upload post

  export function fetchUpPost( title, address, text, img ){
    const formData = new FormData();
  formData.append('title', title);
  formData.append('address', address);
  formData.append('text', text);
  formData.append('avatar', img);


    return fetch('/api/post', {
      method: 'PATCH',
      body: formData,
    }).catch( () => Promise.reject({ error: 'networkError' }) )
    .then( response => {
      if (response.ok) {
        return response.json();
      }
      return response.json()
      .catch( error => Promise.reject({ error }) )
      .then( err => Promise.reject(err) );
    });
  
  }


  //fetch 
                                                 
  export function fetchUpComment( comment, id  ){
    
    return fetch('/api/comment', {
      method: 'PATCH',
      headers: new Headers({
        'content-type': 'application/json'
      }),
      body: JSON.stringify({ comment , id }),
    }).catch( () => Promise.reject({ error: 'networkError' }) )
    .then( response => {
      if (response.ok) {
        return response.json();
      }
      return response.json()
      .catch( error => Promise.reject({ error }) )
      .then( err => Promise.reject(err) );
    });


  } 


  export function fetchMyCards(){
 
    return fetch('/api/myCards', {
        method: 'GET',
      })
    .catch( () => Promise.reject({ error: 'networkError' }) )
    .then(
        response => {
            if (response.ok) {
              return response.json();
            }
            return response.json()}
    )
}

export function fetchDeleteCard(id){
  return fetch('/api/cards', {
    method: 'DELETE',
    headers: new Headers({
      'content-type': 'application/json'
    }),
    body: JSON.stringify({id}),
  })
  .catch( () => Promise.reject({ error: 'networkError' }) )
  .then( response => {
    if (response.ok) {
      return response.json();
    }
    return response.json()
    .catch( error => Promise.reject({ error }) )
    .then( err => Promise.reject(err) );
  });
}
