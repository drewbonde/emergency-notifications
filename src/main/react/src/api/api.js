/* global token */
/* global IS_DEMO */

export const getNumbers = async (n1) => {
  if (IS_DEMO) {
    return [
      {
        type: 'TEXT',
        number: 2485550100,
      },
      {
        type: 'CELL',
        number: 2485550123,
      },
      {
        type: 'HOME',
        number: 2485550199,
      },
    ]
  }
  try {
    const response = await fetch('/emergency-notifications-soffit/api/v1/numbers', {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + token,
      },
    })
    const data = await response.json()
    return data
  } catch (err) {
    return err
  }
}

export const updateNumbers = async (n2) => {
  if(IS_DEMO) {
    return
  }
  try {
    let phones_post = [
      {
        type: 'TEXT',
        number: n2.newTextNum || '',
      },
      {
        type: 'CELL',
        number: n2.newCellNum || '',
      },
      {
        type: 'HOME',
        number: n2.newHomeNum || '',
      },
    ]

    const response = await fetch('/emergency-notifications-soffit/api/v1/numbers', {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + token,
      },
      body: JSON.stringify(phones_post),
    })

    if(!response.ok) {
      console.error('Error updating numbers.')
      return
    }

    const data = await response.json()
  } catch (err) {
    return err
  }
}
