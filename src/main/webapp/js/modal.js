// modal.js

export const createModal = (title, content, type, data) => {
    const modalEl = document.createElement('div');
    modalEl.classList.add('modal', 'fade');
    modalEl.setAttribute('id', 'errorModal');
    modalEl.setAttribute('tabindex', '-1');
    modalEl.setAttribute('role', 'dialog');
    modalEl.setAttribute('aria-labelledby', 'errorModalLabel');
    modalEl.setAttribute('aria-hidden', 'true');

    switch (type) {
        case "x":
            modalEl.innerHTML = `
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="errorModalLabel">${title}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          ${content}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  `;
            break;
        case 'y':
            modalEl.innerHTML = `
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header bg-danger text-white">
          <h5 class="modal-title" id="errorModalLabel">${title}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body text-danger">
          ${content}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">OK</button>
        </div>
      </div>
    </div>
  `;
            break;
        case 'z':

            break;
        default:
            modalEl.innerHTML = `
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="errorModalLabel">${title}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          ${content}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  `;
            break;
    }


    return modalEl;
};


export const showModal = () => {
    const modalEl = createModal("Error!", "This is Error!", "y");
    document.body.appendChild(modalEl);

    // Get the main content container
    const mainContent = document.getElementById('main-container');

    // Add the blur effect to the main content container
    mainContent.classList.add('blur-effect');

    // Add classes to body element to prevent scrolling and apply modal styles
    document.body.classList.add('modal-open');
    $(modalEl).modal('show');

    // Remove modal and blur effect when modal is closed
    $(modalEl).on('hidden.bs.modal', () => {
        document.body.removeChild(modalEl);
        mainContent.classList.remove('blur-effect');
        document.body.classList.remove('modal-open');
    });
};